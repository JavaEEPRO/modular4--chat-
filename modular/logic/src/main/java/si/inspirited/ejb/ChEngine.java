package si.inspirited.ejb;

import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import si.inspirited.domain.entityes.MessageEntity;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author  Lord Jesus
 */

@Stateless
public class ChEngine {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public boolean checkPassword(String data, String password){
        if(StringUtils.isEmpty(data) || StringUtils.isEmpty(password)){
            return false;
        }

        MessageEntity messageEntity = entityManager.find(MessageEntity.class, data);
        if(messageEntity == null){
            return false;
        }

        if(password.equals(messageEntity.getPassword())){
            return true;
        }

        return false;
    }

    public boolean createMessage(String data, String password){
        if(StringUtils.isEmpty(data)){
            return false;
        }

        MessageEntity messageEntity = entityManager.find(MessageEntity.class, data);
        if(messageEntity != null){
            return false;
        }

        messageEntity = new MessageEntity();
        Date date = new Date();
        long time = date.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("dd MMM hh:mm:ss");

        messageEntity.setData(data);
        messageEntity.setTime(format.format(calendar.getTime()));
        messageEntity.setAuthor("user: ");
        messageEntity.setPassword(generateColor());
        entityManager.persist(messageEntity);

        return true;
    }

    public List<MessageEntity> getAllMessages(){
        Query query = entityManager.createQuery("select entity from MessageEntity entity");

        List<MessageEntity> rl = query.getResultList();
        List<MessageEntity> reverse = new ArrayList<MessageEntity>();

        for (MessageEntity message: rl) {
            reverse.add(0, message);
        }
        return reverse;
    }

    int i = 0;
    public String generateColor() {
        i++;
        if (i%2==0) {return "#7fffd4";}
        return "#ffe4c4";
    }
}