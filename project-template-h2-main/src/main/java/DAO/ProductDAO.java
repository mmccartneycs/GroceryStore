public class ProductDAO{

    public User getAllProducts(Product product){
        Connection connection = ConnectionUtil.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            String sql = "select * from product;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Product product = new Product(rs.getInt("upc"),
                        rs.getInt("price"),
                        rs.getString("name"),
                        rs.getString("description"));
                products.add(product);
            }
        }catch(SQLException e){
            System.out.println(e.getProduct());
        }
        return products;
    }

    public User getProductByName(String filter){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "select * from product where name  = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, filter);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Product retProduct = new Product(rs.getInt("upc"),
                        rs.getInt("price"),
                        rs.getString("name"),
                        rs.getString("description"));
                return retProduct;
            }
        }catch(SQLException e){
            System.out.println(e.getProduct());
        }
        return null;
    }

    public User getProductsByFilters(String filter){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //TODO: create field for tags. change sql description to tags.
            String sql = "SELECT * FROM product WHERE description contains '?';";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, filter);
            ResultSet rs = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getInt("upc"),
                        rs.getInt("price"),
                        rs.getString("name"),
                        rs.getString("description"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e.getProduct());
        }
        return null;
    }


}