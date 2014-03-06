package ua.miratech.zhukov.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.domain.User;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

	@Query("{ md5: ?0 }" )
	Book findByMD5(String md5);

	@Query("{ $query: { owner.$id: ?0 }, $orderby: { publicationDate : -1 } }")
	List<Book> findBooksByOwner(ObjectId ownerId);

	@Query("{ $query: { $or: [ { sharedType: 'PUBLIC' }, " +
			"{ $and: [ { sharedFor.$id: ?0 }, { sharedFor.$id: { $exists: true } } ] } ] }  }, $orderby: { publicationDate : -1 } }")
	List<Book> findLastBooks(ObjectId currentUserId); //Pageable pageable

	@Query("{ $and: [ { storedIndex: { $in: ?1 } }," +
			" { $or: [ { sharedType: 'PUBLIC' }, { owner.$id: ?0 }, { $and: [ { sharedFor.$id: ?0 }, { sharedFor.$id: { $exists: true } } ] } ] }   ] } ] }")
	List<Book> findBooksWithStoredIndexes(ObjectId currentUserId, List<String> storedIndexes);

}