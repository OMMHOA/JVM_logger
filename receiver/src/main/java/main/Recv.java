package main;

import dal.MessageDao;
import entity.Message;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"dal"})
@EntityScan(basePackages = {"entity"})
public class Recv implements CommandLineRunner {

  private final static String QUEUE_NAME = "hello";

  private final MessageDao messageDao;

  @Autowired
  public Recv(MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  @Override
  public void run(String... args)throws Exception{
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
        Message messageEntity = new Message();
        messageEntity.setMessage(message);
        messageDao.save(messageEntity);
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Recv.class, args);
  }


}