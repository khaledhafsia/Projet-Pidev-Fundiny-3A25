    package org.example.Services;

    import org.example.interfaces.Iservice;
    import org.example.interfaces.IserviceINV;
    import org.example.Entities.investissements;
    import org.example.utils.MyDataBase;

    import java.sql.*;
    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;

    public  class serviceInvestissements {
        private static Connection cnx;
        public serviceInvestissements(){
            cnx=MyDataBase.getInstance().getCnx();
        }
       // @Override
        public static void add(investissements investissement, int user_id,int project_id) {
            String qry ="INSERT INTO `investissements`(`user_id`, `montant`,`description`, `date`,`projet_id `) VALUES (?,?,?,?,?)";

            //String qry ="INSERT INTO `investissements`(`user_id`, `montant`,`description`, `date`) VALUES (?,?,?,?)";
            try {
                PreparedStatement stm = cnx.prepareStatement(qry);
                stm.setInt(1, user_id);
                stm.setDouble(2, investissement.getMontant());
                stm.setString(3, investissement.getDescription());
                Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
                investissement.setDate(currentTimestamp);
                stm.setTimestamp(4, currentTimestamp);
                stm.setInt(5, investissement.getProjetID());
                stm.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

     //   @Override
        public ArrayList<investissements> getAll() {
            ArrayList<investissements> invs = new ArrayList<>();
            String qry ="SELECT * FROM `investissements`";
            try {
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(qry);
                while (rs.next()){
                    investissements i = new investissements();

                    i.setInvID(rs.getInt(1));
                    //i.setUserID(rs.getInt(2));
                    i.setProjetID(rs.getInt(3));
                    i.setMontant(rs.getDouble(4));
                    i.setDescription(rs.getString(5));

                    // Retrieve the Timestamp from the result set and set it to the investissements object
                    Timestamp timestamp = rs.getTimestamp(6);
                    i.setDate(timestamp);

                    invs.add(i);

                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return invs;
        }

     //   @Override
        public void update(investissements investissements) {
            String qry = "UPDATE `investissements` SET `id`=?, `projet_id`=?, `montant`=?, `description`=?, `date`=? WHERE `invID`=?";
            try {
                PreparedStatement stm = cnx.prepareStatement(qry);
                stm.setInt(1, investissements.getUserID());
                stm.setInt(2, investissements.getProjetID());
                stm.setDouble(3, investissements.getMontant());
                stm.setString(4, investissements.getDescription());
                stm.setTimestamp(5, investissements.getDate()); // Use the existing date value
                stm.setInt(6, investissements.getInvID()); // Use the existing invID value

                int rowsAffected = stm.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("Aucune ligne mise à jour pour l'investissement avec l'ID : " + investissements.getInvID());
                } else {
                    System.out.println("Mise à jour réussie pour l'investissement avec l'ID : " + investissements.getInvID());
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la mise à jour de l'investissement : " + e.getMessage());
            }
        }

       // @Override
        public boolean delete(investissements investissements) {
            String qry = "DELETE FROM investissements WHERE `invID`=?";
            try {
                PreparedStatement stm = cnx.prepareStatement(qry);
                stm.setInt(1, investissements.getInvID());

                int rowsAffected = stm.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("Aucune ligne supprimée pour l'investissement' l'ID : " + investissements.getInvID());
                    return false; // Aucune ligne supprimée
                } else {
                    System.out.println("investissement supprimé avec succès, ID : " + investissements.getInvID());
                    return true; // Suppression réussie
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la suppression de l'investissement : " + e.getMessage());
                return false; // Erreur lors de la suppression
            }
        }

        public investissements getInvestissementById(int id) {
            String qry = "SELECT * FROM `investissements` WHERE `invID` = ?";
            try {
                PreparedStatement stm = cnx.prepareStatement(qry);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    investissements i = new investissements();
                    i.setInvID(rs.getInt("invID"));
                    i.setUserID(rs.getInt("id"));
                    i.setProjetID(rs.getInt("projet_id"));
                    i.setMontant(rs.getDouble("montant"));
                    i.setDescription(rs.getString("description"));
                    i.setDate(rs.getTimestamp("date"));
                    return i;
                }
            } catch (SQLException e) {
                System.out.println("Error getting investissement by ID: " + e.getMessage());
            }
            return null; // Return null if no investissement with the given ID is found
        }
        public ArrayList<investissements> getInvestmentsByUserId(int userId) {
            ArrayList<investissements> userInvestments = new ArrayList<>();
            String query = "SELECT * FROM investissements WHERE user_id = ?";
            try {
                PreparedStatement statement = cnx.prepareStatement(query);
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    investissements investment = new investissements(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            //resultSet.getInt("project_id"),
                            resultSet.getDouble("montant"),
                            resultSet.getString("description"),
                            resultSet.getTimestamp("date")
                    );
                    userInvestments.add(investment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return userInvestments;
        }

        public List<investissements> FetchInvestmentsByUserId(int userId) {
            List<investissements> investments = new ArrayList<>();
            String query = "SELECT * FROM `investissements` WHERE `user_id` = ?";

            try (PreparedStatement statement = cnx.prepareStatement(query)) {
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int projectId = resultSet.getInt("project_id");
                    double montant = resultSet.getDouble("montant");
                    String description = resultSet.getString("description");
                    Timestamp date = resultSet.getTimestamp("date");

                    investissements investment = new investissements(id, userId, projectId, montant, description, date);
                    investments.add(investment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return investments;
        }


    }
