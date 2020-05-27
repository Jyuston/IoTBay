package uts.isd.model;

public class Staff extends Account {
    private boolean isAdmin;

    public Staff(String ID, String firstName, String lastName, String email, String password, String contactNumber, boolean isAdmin) {
        super(ID, firstName, lastName, email, password, contactNumber);
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

//    public static Staff create(String firstName, String lastName, String email, String password, boolean isAdmin) {
//        String ID;
//        try {
//            ID = AccountDAO.getNextAvailableID();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        Staff createdCustomer = new Staff(
//                ID,
//                firstName,
//                lastName,
//                email,
//                password,
//                isAdmin
//        );
//
//        try {
//            DAO.save(createdCustomer);
//        } catch (SQLException err) {
//            err.printStackTrace();
//            return null;
//        }
//
//        return createdCustomer;
//    }
}
