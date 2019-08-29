package edm.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee implements Comparable<Employee>{

    private StringProperty name;
    private StringProperty position;
    private StringProperty birth;
    private StringProperty mothersName;
    private StringProperty taxNumber;
    private StringProperty socialNumber;
    private StringProperty permAddress;
    private StringProperty tempAddress;
    private StringProperty workPlace;
    private StringProperty email;
    private BooleanProperty active;
    private StringProperty payrollPath;

    public Employee(String name, String position, String birth, String mothersName,
                    String taxNumber, String socialNumber, String permAddress,
                    String tempAddress, String workPlace, String email,
                    String payrollPath, boolean active) {
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.birth = new SimpleStringProperty(birth);
        this.mothersName = new SimpleStringProperty(mothersName);
        this.taxNumber = new SimpleStringProperty(taxNumber);
        this.socialNumber = new SimpleStringProperty(socialNumber);
        this.permAddress = new SimpleStringProperty(permAddress);
        this.tempAddress = new SimpleStringProperty(tempAddress);
        this.workPlace = new SimpleStringProperty(workPlace);
        this.email = new SimpleStringProperty(email);
        this.active = new SimpleBooleanProperty(active);
        this.payrollPath = new SimpleStringProperty(payrollPath);
    }

    public Employee(String name) {
        this(name, "","", "", "", "", "", "", "","", "", false);
    }

    public Employee() {
        this("", "", "", "", "", "", "", "", "","","", false);
    }

    public String getName() {
        return name.get();
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getBirth() {
        return birth.get();
    }

    public StringProperty birthProperty() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth.set(birth);
    }

    public String getMothersName() {
        return mothersName.get();
    }

    public StringProperty mothersNameProperty() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName.set(mothersName);
    }

    public String getTaxNumber() {
        return taxNumber.get();
    }

    public StringProperty taxNumberProperty() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber.set(taxNumber);
    }

    public String getSocialNumber() {
        return socialNumber.get();
    }

    public StringProperty socialNumberProperty() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber.set(socialNumber);
    }

    public String getPermAddress() {
        return permAddress.get();
    }

    public StringProperty permAddressProperty() {
        return permAddress;
    }

    public void setPermAddress(String permAddress) {
        this.permAddress.set(permAddress);
    }

    public String getTempAddress() {
        return tempAddress.get();
    }

    public StringProperty tempAddressProperty() {
        return tempAddress;
    }

    public void setTempAddress(String tempAddress) {
        this.tempAddress.set(tempAddress);
    }

    public String getWorkPlace() {
        return workPlace.get();
    }

    public StringProperty workPlaceProperty() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace.set(workPlace);
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public String getPayrollPath() {
        return payrollPath.get();
    }

    public StringProperty payrollPathProperty() {
        return payrollPath;
    }

    public void setPayrollPath(String payrollPath) {
        this.payrollPath.set(payrollPath);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public int compareTo(Employee o) {
        Employee employee = (Employee) o;
        return this.getName().compareTo(employee.getName());
    }
}