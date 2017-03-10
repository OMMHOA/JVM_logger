package dal;

import entity.Message;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface MessageDao extends CrudRepository<Message, Integer> {
}
