<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<div class="page-header">
			<h1>Upload new books</h1>
		</div>

		<form enctype="multipart/form-data" method="POST" action="rest/controller/upload" id="fileupload">
			<!-- Redirect browsers with JavaScript disabled to the origin page -->
			<noscript>&lt;input type="hidden" name="redirect" value="http://blueimp.github.io/jQuery-File-Upload/"&gt;</noscript>
			<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
			<div class="row fileupload-buttonbar">
				<div class="col-lg-7">
					<!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" multiple="" name="files[]">
                </span>
					<button class="btn btn-primary start" type="submit">
						<i class="glyphicon glyphicon-upload"></i>
						<span>Start upload</span>
					</button>
					<button class="btn btn-warning cancel" type="reset">
						<i class="glyphicon glyphicon-ban-circle"></i>
						<span>Cancel upload</span>
					</button>
					<button type="button" class="btn btn-danger delete">
						<i class="glyphicon glyphicon-trash"></i>
						<span>Delete</span>
					</button>
					<input type="checkbox" class="toggle">
					<!-- The global file processing state -->
					<span class="fileupload-process"></span>
				</div>
				<!-- The global progress state -->
				<div class="col-lg-5 fileupload-progress fade">
					<!-- The global progress bar -->
					<div aria-valuemax="100" aria-valuemin="0" role="progressbar" class="progress progress-striped active">
						<div style="width:0%;" class="progress-bar progress-bar-success"></div>
					</div>
					<!-- The extended global progress state -->
					<div class="progress-extended">&nbsp;</div>
				</div>
			</div>
			<!-- The table listing the files available for upload/download -->
			<table class="table table-striped" role="presentation"><tbody class="files"></tbody></table>
		</form>

<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>

<script type="text/javascript" charset="UTF-8" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>

<script src="<c:url value="/plugins/jquery-ui/ui/jquery.ui.widget.js" />"></script>
<script src="<c:url value="/plugins/file-upload/js/jquery.iframe-transport.js" />"></script>
<script src="<c:url value="/plugins/file-upload/js/jquery.fileupload.js" />"></script>

<!-- bootstrap just to have good looking page -->
<link href="<c:url value="/plugins/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
<script type="text/javascript" src="<c:url value="/plugins/bootstrap/js/bootstrap.min.js" />"></script>
<script>
	!function (a) {
		"use strict";
		var b = function (a, c) {
			var d = /[^\w\-\.:]/.test(a) ? new Function(b.arg + ",tmpl", "var _e=tmpl.encode" + b.helper + ",_s='" + a.replace(b.regexp, b.func) + "';return _s;") : b.cache[a] = b.cache[a] || b(b.load(a));
			return c ? d(c, b) : function (a) {
				return d(a, b)
			}
		};
		b.cache = {}, b.load = function (a) {
			return document.getElementById(a).innerHTML
		}, b.regexp = /([\s'\\])(?!(?:[^{]|\{(?!%))*%\})|(?:\{%(=|#)([\s\S]+?)%\})|(\{%)|(%\})/g, b.func = function (a, b, c, d, e, f) {
			return b ? {"\n": "\\n", "\r": "\\r", "	": "\\t", " ": " "}[b] || "\\" + b : c ? "=" === c ? "'+_e(" + d + ")+'" : "'+(" + d + "==null?'':" + d + ")+'" : e ? "';" : f ? "_s+='" : void 0
		}, b.encReg = /[<>&"'\x00]/g, b.encMap = {"<": "&lt;", ">": "&gt;", "&": "&amp;", '"': "&quot;", "'": "&#39;"}, b.encode = function (a) {
			return(null == a ? "" : "" + a).replace(b.encReg, function (a) {
				return b.encMap[a] || ""
			})
		}, b.arg = "o", b.helper = ",print=function(s,e){_s+=e?(s==null?'':s):_e(s);},include=function(s,d){_s+=tmpl(s,d);}", "function" == typeof define && define.amd ? define(function () {
			return b
		}) : a.tmpl = b
	}(this);
</script>
<script src="<c:url value="/plugins/file-upload/js/jquery.fileupload-process.js" />"></script>
<script src="<c:url value="/plugins/file-upload/js/jquery.fileupload-validate.js" />"></script>
<script src="<c:url value="/plugins/file-upload/js/jquery.fileupload-ui.js" />"></script>
<script src="<c:url value="/plugins/main.js" />"></script>