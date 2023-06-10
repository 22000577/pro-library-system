package Database.Customers;

import Database.InvalidPermissionsException;

public class Advanced implements Customer {
    Customers globalCustomersObject;

    public Advanced() {
        globalCustomersObject = new Customers();
    }

    @Override
    public void addUser(String username, String password, String contact, String email) throws Exception {
        globalCustomersObject.addUser(username, password, contact, email);
    }

    @Override
    public void editUserInformation(String username, String objectKey, String objectValue) throws InvalidPermissionsException {
        globalCustomersObject.editUserInformation(username, objectKey, objectValue);
    }

    @Override
    public void removeUser(String username) {
        globalCustomersObject.removeUser(username);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return globalCustomersObject.isUsernameTaken(username);
    }

    @Override
    public boolean validateLogin(String username, String password) {
        return globalCustomersObject.validateLogin(username, password);
    }

    @Override
    public Customers[] getAllUsers() {
        return new Customers[0];
    }

    @Override
    public Customers getUser(String username) {
        return globalCustomersObject.getUser(username);
    }
}
