public class UserService{
    private UserDAO userDAO;


    public User addUser(User user){
        return userDAO.addUser(user);
    }

    public User validateUser(User user){
        return userDAO.validateUser(user);
    }

}