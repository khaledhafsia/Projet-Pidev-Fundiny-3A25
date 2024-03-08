    package org.example.Services;

    import org.example.Entities.*;
    import org.example.utils.MyDataBase;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    public class ServiceUser {
        private static final Logger logger = Logger.getLogger(ServiceUser.class.getName());

        private static Connection cnx;
        Statement stm;


        public ServiceUser() {
            cnx = MyDataBase.getInstance().getCnx();
            if (cnx == null) {
                System.out.println("Database connection is null. Check database configuration.");
            }
        }

        public static void insertUser(User user) throws SQLException {
            String req = "INSERT INTO `user` (`nom`, `prenom`, `email`, `password`, `role`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().name());
            // ps.setString(6, attributeValue);
            ps.executeUpdate();
        }

        public static User verifyUser(String email) throws SQLException {
            User user = null;
            String req = "SELECT id, nom, prenom, email, password, role, ban_state FROM `user` WHERE `email` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //boolean banState = rs.getBoolean("ban_state");
                user = new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        User.role.valueOf(rs.getString("role")),
                        rs.getBoolean("ban_state")
                        //banState
                );
            }
            return user;
        }


        public User validateUser(String email, String password) {
            User user = null;
            try {
                user = verifyUser(email);
                if (user != null && user.getPassword().equals(password)) {
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }


        public User.role getUserRole(String email) {
            try {
                User user = verifyUser(email);
                if (user != null) {
                    return user.getRole();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return User.role.ADMIN;
        }

        public List<User> getAllUsers() {
            List<User> userList = new ArrayList<>();
            String query = "SELECT * FROM `user`";

            try (PreparedStatement statement = cnx.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {


                userList.clear();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    User.role userRole = User.role.valueOf(resultSet.getString("role"));
                    User user;

                    user = new User(id, nom, prenom, email, password, userRole);
/*
                    if (userRole == User.role.Funder) {
                        user = new Funder(id, nom, prenom, email, password, userRole);

                        List<investissements> investments = getInvestmentsForFunder(id);
                        ((Funder) user).setInvestmentsList(investments);
                    }
                    if (userRole == User.role.Owner) {
                        user = new Owner(id, nom, prenom, email, password, userRole);

                        List<Projet> projects = getProjetForUser(id);
                        ((Owner) user).setProjectList(projects);
                    } else {
                        user = new User(id, nom, prenom, email, password, userRole);
                    }


 */


                    userList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "SQL Exception: " + e.getMessage());
            }
            return userList;
        }

//        public List<Projet> getProjetForUser(int userId) {
//            List<Projet> projetsList = new ArrayList<>();
//            String query = "SELECT * FROM `projet` WHERE `user_id` = ?";
//            try (PreparedStatement statement = cnx.prepareStatement(query)) {
//                statement.setInt(1, userId);
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    while (resultSet.next()) {
//                        int id = resultSet.getInt("id");
//                        //String nomPr = resultSet.getString("nomPr");
//                        //int CA = resultSet.getInt("CA");
//                        //Date dateD = resultSet.getDate("dateD");
//
//                        //java.sql.Timestamp dateD = resultSet.getTimestamp("date");
//                        Projet inv = new Projet(id);
//                        //Projet inv = new Projet(id, nomPr, CA, dateD);
//                        projetsList.add(inv);
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return projetsList;
//        }
//
//        public List<investissements> getInvestmentsForFunder(int userId) {
//            List<investissements> investmentsList = new ArrayList<>();
//            String query = "SELECT * FROM `projet` WHERE `user_id` = ?";
//            try (PreparedStatement statement = cnx.prepareStatement(query)) {
//                statement.setInt(1, userId);
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    while (resultSet.next()) {
//                        int invID = resultSet.getInt("id");
//                        int user_id = resultSet.getInt("user_id");
//                        int projetID = resultSet.getInt("projet_id");
//                        double montant = resultSet.getDouble("montant");
//                        String description = resultSet.getString("description");
//                        java.sql.Timestamp date = resultSet.getTimestamp("date");
//
//                        investissements inv = new investissements(invID, user_id, projetID, montant, description, date);
//
//                        investmentsList.add(inv);
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return investmentsList;
//        }


        public void updateUserAttribute(int userId, String attributeType, String attributeValue) {
            try {
                String query = "UPDATE `user` SET `" + attributeType + "` = ? WHERE id = ?";
                PreparedStatement statement = cnx.prepareStatement(query);
                statement.setString(1, attributeValue);
                statement.setInt(2, userId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteUser(int id) throws SQLException {
            String req = "DELETE FROM `user` WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("No rows were deleted for ID " + id);
            } else {
                System.out.println(rowsDeleted + " rows were deleted for ID " + id);
            }
        }

        public String fetchUserPasswordByMail(String email) {
            try {
                User user = verifyUser(email);
                if (user != null) {
                    return user.getPassword();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void banUser(int userId) {
            try {
                String query = "UPDATE `user` SET `ban_state` = ? WHERE id = ?";
                PreparedStatement statement = cnx.prepareStatement(query);
                statement.setBoolean(1, true);
                statement.setInt(2, userId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void unbanUser(int userId) {
            try {
                String query = "UPDATE `user` SET `ban_state` = ? WHERE id = ?";
                PreparedStatement statement = cnx.prepareStatement(query);
                statement.setBoolean(1, false);
                statement.setInt(2, userId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

//        public List<User> searchUsersByName(String name) {
//            List<User> userList = new ArrayList<>();
//            String query = "SELECT * FROM `user` WHERE `nom` LIKE ? OR `prenom` LIKE ?";
//            try (PreparedStatement statement = cnx.prepareStatement(query)) {
//                // Set the parameter for both nom and prenom
//                statement.setString(1, "%" + name + "%");
//                statement.setString(2, "%" + name + "%");
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    while (resultSet.next()) {
//                        int id = resultSet.getInt("id");
//                        String nom = resultSet.getString("nom");
//                        String prenom = resultSet.getString("prenom");
//                        String email = resultSet.getString("email");
//                        String password = resultSet.getString("password");
//                        User.role userRole = User.role.valueOf(resultSet.getString("role"));
//                        User user;
//
//                        if (userRole == User.role.Funder) {
//                            user = new Funder(id, nom, prenom, email, password, userRole);
//
//                            List<investissements> investments = getInvestmentsForFunder(id);
//                            ((Funder) user).setInvestmentsList(investments);
//                            if (userRole == User.role.Owner) {
//                                user = new Owner(id, nom, prenom, email, password, userRole);
//
//                                List<Projet> Projet = getProjetForUser(id);
//                                ((Owner) user).setProjectList(Projet);
//                            } else {
//                                user = new User(id, nom, prenom, email, password, userRole);
//                            }
//                            userList.add(user);
//                        }
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                return userList;
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//            public List<User> searchUsersByPrenom (String name){
//                List<User> userList = new ArrayList<>();
//                String query = "SELECT * FROM `user` WHERE `role` LIKE ?";
//                try (PreparedStatement statement = cnx.prepareStatement(query)) {
//                    statement.setString(1, "%" + name + "%");
//                    try (ResultSet resultSet = statement.executeQuery()) {
//                        while (resultSet.next()) {
//                            int id = resultSet.getInt("id");
//                            String nom = resultSet.getString("nom");
//                            String prenom = resultSet.getString("prenom");
//                            String email = resultSet.getString("email");
//                            String password = resultSet.getString("password");
//                            User.role userRole = User.role.valueOf(resultSet.getString("role"));
//                            User user = new User(id, nom, prenom, email, password, userRole);
//
//                            if (userRole == User.role.Funder) {
//                                user = new Funder(id, nom, prenom, email, password, userRole);
//
//                                List<investissements> investments = getInvestmentsForFunder(id); // Implement this method
//                                ((Funder) user).setInvestmentsList(investments);
//                                if (userRole == User.role.Owner) {
//                                    user = new Owner(id, nom, prenom, email, password, userRole);
//
//                                    List<Projet> Projet = getProjetForUser(id);
//                                    ((Owner) user).setProjectList(Projet);
//                                } else {
//                                    user = new User(id, nom, prenom, email, password, userRole);
//                                }
//                                userList.add(user);
//                            }
//
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    return userList;
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }


            public List<User> getUsersByRole (User.role role){
                List<User> userList = new ArrayList<>();
                String query = "SELECT * FROM `user` WHERE `role` = ?";
                try (PreparedStatement statement = cnx.prepareStatement(query)) {
                    statement.setString(1, role.name());
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt("id");
                            String nom = resultSet.getString("nom");
                            String prenom = resultSet.getString("prenom");
                            String email = resultSet.getString("email");
                            String password = resultSet.getString("password");
                            User.role userRole = User.role.valueOf(resultSet.getString("role"));
                            User user = new User(id, nom, prenom, email, password, userRole);

                            /*
                            if (userRole == User.role.Funder) {
                                user = new Funder(id, nom, prenom, email, password, userRole);

                                List<investissements> investments = getInvestmentsForFunder(id); // Implement this method
                                ((Funder) user).setInvestmentsList(investments);
                                if (userRole == User.role.Owner) {
                                    user = new Owner(id, nom, prenom, email, password, userRole);

                                    List<Projet> Projet = getProjetForUser(id);
                                    ((Owner) user).setProjectList(Projet);
                                } else {
                                    user = new User(id, nom, prenom, email, password, userRole);
                                }
                                userList.add(user);

                           }
                             */
                            user = new User(id, nom, prenom, email, password, userRole);
                            userList.add(user);

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return userList;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }


        }

