package Customer;

import Exceptions.ExceptionManager;
import Exceptions.NullpointerException;
import Group.Group;

import java.util.Arrays;
import java.util.Comparator;
import Group.Parameter;
import Group.Groups;
import Group.GroupType;

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


    public void deleteCustomerBySerialId(String id) {
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


    public boolean updateCustomerBySerialId(String id, String name, String customerId, int spendHour, int spendMoney) {
        Customer customer = getBySerialId(id);
        if (customer != null) {
            customer.setName(name);
            customer.setCustomerId(customerId);
            customer.setSpendMoney(spendMoney);
            customer.setSpendHour(spendHour);
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

    public Customer getBySerialId(String id) {
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


    public Customer getByCustomerId(String customerId) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i] != null) {
                if (customers[i].getId().equals(customerId)) {
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

    public void printCustomerCountBySerialId(){
        if(!isEmpty()) {
            String minId = getMinId();
            String maxId = getMaxId();
            System.out.println("고객 수 : " + customerCount);
            System.out.println(minId + "~" + maxId + ", 중에서 선택해주세요.");
        }
        else{
            ExceptionManager.catchNoCustomersException();
        }
    }

    public void printNoneGroupByClassifiedCustomersList(Customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 0) {
                    System.out.println(customers[i].toString());
                }
            }
        }
    }

    public void printGeneralGroupByClassifiedCustomersList(Customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 1) {
                    System.out.println(customers[i].toString());
                }
            }
        }
    }

    public void printVIPGroupByClassifiedCustomersList(Customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 2) {
                    System.out.println(customers[i].toString());
                }
            }
        }
    }

    public void printVVIPGroupByClassifiedCustomersList(Customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 3) {
                    System.out.println(customers[i].toString());
                }
            }
        }
    }


    public Customer[] sortByNameAsc() {
        Customer[] customers1 = Arrays.copyOf(customers, customerCount);
        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o1.getName().compareTo(o2.getName());
                }
                return 0;
            }
        });
        return customers1;
    }

    public Customer[] sortByNameDesc() {
        Customer[] customers1 = Arrays.copyOf(customers, customerCount);

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o2.getName().compareTo(o1.getName());
                }
                return 0;
            }
        });
        return customers1;
    }



    public Customer[] sortBySpendMoneyDesc() {
        Customer[] customers1 = Arrays.copyOf(customers, customerCount);

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o2.getSpendMoney() - o1.getSpendMoney();
                } else {
                    return 0;
                }
            }
        });
        return customers1;
    }

    public Customer[] sortBySpendMoneyAsc() {
        Customer[] customers1 = Arrays.copyOf(customers, customerCount);

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o1.getSpendMoney()- o2.getSpendMoney() ;
                } else {
                    return 0;
                }
            }
        });
        return customers1;
    }
    public Customer[] sortBySpendHourDesc() {
        Customer[] customers1 = Arrays.copyOf(customers, customerCount);

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o2.getSpendHour() - o1.getSpendHour();
                } else {
                    return 0;

                }
            }
        });
        return customers1;
    }

    public Customer[] sortBySpendHourAsc() {
        Customer[] customers1 = Arrays.copyOf(customers, customerCount);

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o1.getSpendHour()- o2.getSpendHour();
                } else {
                    return 0;

                }
            }
        });
        return customers1;
    }

    public void initCustomersGroup() {
        Customer[] temp = Arrays.copyOf(customers, customerCount);
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                temp[i].setGroup(Group.of(Parameter.of(0, 0), 0));
            }
        }
        customers=temp;
    }

    public void classifyCustomerByGroups(Groups groups) { //중간에 작업이 잘못되었을때 방지를 위해 배열 복사후 작업
        try{
        initCustomersGroup();
        Customer[] temp = Arrays.copyOf(customers, customerCount);
        for (int i = 3; i > 0; i--) {
            if (groups.getGroups()[i].getParameter().getSpendHourStandard() != 0 && groups.getGroups()[i].getParameter().getSpendMoneyStandard() != 0) {
                for (int j = 0; j < temp.length; j++) {
                    if(temp[j]!=null){
                        if(temp[j].getGroup().getGroupType()== GroupType.NONE){
                            if (temp[j].getSpendHour() >= groups.getGroups()[i].getParameter().getSpendHourStandard() && temp[j].getSpendMoney() >= groups.getGroups()[i].getParameter().getSpendMoneyStandard()) {
                                temp[j].setGroup(groups.getGroups()[i]);
                            }}
                    }
                }
            }
        }
        customers = temp;
    }catch (Exception e){
            ExceptionManager.catchUnknownException();
        }
    }

}


