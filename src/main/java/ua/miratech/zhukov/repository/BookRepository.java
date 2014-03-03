package ua.miratech.zhukov.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.domain.User;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

	@Query("{ md5: ?0 }, { storedIndex: 1, _id:0 }")
	String findStoredIndexByMD5(String md5);

	@Query("{ owner.$id: ?0 }")
	List<Book> findBooksByOwner(ObjectId ownerId);

	@Query("{  }")
	List<Book> findLastBooks(ObjectId ownerId);

	@Query("{ $id: { $in: ?0 ] } }")
	List<Book> findBooksByOwner(List<String> storedIndexes);

}
