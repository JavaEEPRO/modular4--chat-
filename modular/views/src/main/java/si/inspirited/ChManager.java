package si.inspirited;

import si.inspirited.ejb.ChEngine;
import si.inspirited.domain.entityes.MessageEntity;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Jesus Lord
 */

@Named
@SessionScoped
public class ChManager implements Serializable{

    private String data;
    private String password;
    private String color;

    private boolean loginSuccess;
    private boolean createSuccess;
    private boolean pickedSuccess;

    @EJB
    private ChEngine chEngine;



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public boolean isCreateSuccess() {
        return createSuccess;
    }

    public boolean isPickedSuccess() {
        return pickedSuccess;
    }

    public void setPickedSuccess(boolean pickedSuccess) {
        this.pickedSuccess = pickedSuccess;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCreateSuccess(boolean createSuccess) {
        this.createSuccess = createSuccess;
    }

    public void checkPassword(){
        loginSuccess = chEngine.checkPassword(data, password);
    }

    public void createMessage(){
        createSuccess = chEngine.createMessage(data, password);
    }

    public List<MessageEntity> getAllMessages(){
        return chEngine.getAllMessages();
    }

    public void generateColor() {}


}