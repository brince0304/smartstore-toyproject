package Customer;

import Exceptions.ExceptionManager;
import Exceptions.NullpointerException;

import java.util.Arrays;

public class Customers {
    private static Customers instance;


    private Customer[] customers = new Customer[5];

    private int customerCount = 0;

    public static Customers getInstance() {
        if (instance == null) {
            instance = new Customers();
        }
        return instance;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    private Customers() {
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }


    public boolean addCustomer(Customer customer) throws NullpointerException {
        if (customer != null) {
            if (customerCount < customers.length) {
                for (int i = 0; i < customers.length; i++) {
                    if (customers[i] == null) {
                        customers[i] = customer;
                        customerCount++;
                        return true;
                    }

                }
            } else {
                grow();
                for (int i = 0; i < customers.length; i++) {
                    if (customers[i] == null) {
                        customers[i] = customer;
                        customerCount++;
                        return true;
                    }
                }
                return false;
            }
        } else {
            ExceptionManager.catchSameCustomerIdException();
            return false;
        }
        return false;
    }


    public void grow() {
        Customer[] temp = Arrays.copyOf(customers, customers.length * 2);
        customers = temp;
    }


    public void deleteCustomerById(String id) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i] != null) {
                if (customers[i].getId().equals(id)) {
                    for (int j = i; j < customerCount - 1; j++) {
                        customers[j] = customers[j + 1];
                    }
                    customers[customerCount - 1] = null;
                    customerCount--;
                    System.out.println("고객이 삭제되었습니다.");
                    trimToSize();
                    return;
                }
            }
        }
    }

    public boolean isEmpty(){
        if(customerCount == 0){
            return true;
        }
        return false;
    }


    public void trimToSize() {
        Customer[] temp = Arrays.copyOf(customers, customerCount);
        customers = temp;
    }


    public boolean updateCustomerById(String id, String name, String customerId, int spendHour, int spendMoney) {
        Customer customer = getById(id);
        if (customer != null) {
            customer.setName(name);
            customer.setCustomerId(customerId);
            customer.setSpendMoney(spendMoney);
            customer.setUseHour(spendHour);
            for (int i = 0; i < customers.length; i++) {
                if (customers[i] != null) {
                    if (customers[i].getId().equals(id)) {
                        customers[i] = customer;
                        return true;


                    }
                }
            }
        }
        return false;
    }

    public Customer getById(String id) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i] != null) {
                if (customers[i].getId().equals(id)) {
                    return customers[i];
                }
            }
        }
        ExceptionManager.catchCustomerIdNotFoundedException();
        return null;
    }


    public Customer getByCustomerId(String id) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i] != null) {
                if (customers[i].getId().equals(id)) {
                    return customers[i];
                }
            }
        }
        ExceptionManager.catchCustomerIdNotFoundedException();
        return null;
    }


    public String getMinId() {
        String minId = customers[0].getId();
        return minId;
    }

    public String getMaxId() {
        String maxId = customers[customerCount-1].getId();
        return maxId;
    }
}


