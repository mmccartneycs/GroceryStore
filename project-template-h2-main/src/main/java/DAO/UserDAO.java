
public class UserDAO{

    public User insertUser(User user){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "insert into user (email, password) values (?,?); " ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUserEmail());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_user_id = pkeyResultSet.getInt("user_id");
                return new Account(generated_user_id, account.getUserEmail(), account.getPassword());
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User getUser(User user) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from user where email = ? and password = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, account.getUserEmail());
        preparedStatement.setString(2, account.getPassword());
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            User existingUser = new User( rs.getInt("user_id"), rs.getString("userEmail"), rs.getString("password"));
            return existingUser;
        }

        return null;
    }
}