package ua.miratech.zhukov.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Iterator;

@Deprecated
public class FictionBookElement implements Iterable<FictionBookElement> {

	private Node element;
	private Iterator<FictionBookElement> iterator;

	public FictionBookElement(Node element) {
		this.element = element;
	}

	public String getName(){
		return element.getNodeName();
	}

	public String getContent(){
		return element.getTextContent();
	}

	@Override
	public Iterator<FictionBookElement> iterator() {
		if (iterator == null) {
			iterator = new FictionBookIterator();
		}
		return iterator;
	}

	private class FictionBookIterator implements Iterator<FictionBookElement> {

		private NodeList list = element.getChildNodes();
		private int position = 1;

		@Override
		public boolean hasNext() {
			return (position < list.getLength() - 1);
		}

		@Override
		public FictionBookElement next() {
			FictionBookElement element = new FictionBookElement(list.item(position));
			position += 2;
			return element;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
