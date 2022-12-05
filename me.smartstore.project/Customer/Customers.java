package Customer;

import Group.Group;

import java.util.Arrays;

import Group.Parameter;
import Group.Groups;
import Group.GroupType;

public class Customers {
    private static Customers instance;

    private final ClassifiedCustomers classifiedCustomers;
    private Customer[] customers = new Customer[5];

    private int customerCount = 0;

    public static Customers getInstance() {
        if (instance == null) {
            instance = new Customers(ClassifiedCustomers.getInstance());
        }
        return instance;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    private Customers(ClassifiedCustomers classifiedCustomers
    ) {
        this.classifiedCustomers = classifiedCustomers;
    }




    public boolean addCustomer(Customer customer) throws NullPointerException { // 고객 추가
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
            return false;
        }
        return false;
    }


    public void grow() { // 배열의 크기를 늘려주는 메소드
        customers = Arrays.copyOf(customers, customers.length * 2);
    }


    public void deleteCustomerBySerialId(String id) { //고유번호로 등록 고객 삭제
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

    public boolean isEmpty(){ // 고객배열이 비어있는지 확인
        return customerCount == 0;
    }


    public void trimToSize() { // 고객이 삭제되면 배열의 크기를 줄여준다.
        customers = Arrays.copyOf(customers, customerCount);
    }


    public boolean updateCustomerBySerialId(String id, String name, String customerId, int spendHour, int spendMoney) {
        Customer customer = getCustomerBySerialId(id);
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
    } // 고객 정보 수정

    public Customer getCustomerBySerialId(String id) { // 고유넘버로 고객 찾기
        for (int i = 0; i < customerCount; i++) {
            if (customers[i] != null) {
                if (customers[i].getId().equals(id)) {
                    return customers[i];
                }
            }
        }
        return null;
    }


    public Customer getByCustomerId(String customerId) { // 고객 아이디로 고객 찾기
        for (int i = 0; i < customerCount; i++) {
            if (customers[i] != null) {
                if (customers[i].getId().equals(customerId)) {
                    return customers[i];
                }
            }
        }
        return null;
    }


    public String getMinId() { // 고유넘버 처음값
        return customers[0].getId();
    }

    public String getMaxId() { //고유넘버 끝값
        return customers[customerCount-1].getId();
    }

    public void printCustomerCountBySerialId(){ //고객수 출력
        if(customerCount == 0){
            return;
        }
            String minId = getMinId();
            String maxId = getMaxId();
            System.out.println("고객 수 : " + customerCount);
            System.out.println(minId + "~" + maxId + ", 중에서 선택해주세요.");

    }

    public void printNoneGroupByClassifiedCustomersList(Customer[] customers) { //그룹에 속하지 않은 고객들의 리스트를 출력하는 메소드
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 0) {
                    System.out.println(customers[i].toString());
                }
            }
        } //사용하지 않는다.
    } //생각해보니 그룹을 분류하지 않고 계속 탐색을 하면 성능이 안좋을 수도 있다.


    public void printGeneralGroupByClassifiedCustomersList(Customer[] customers) { // 일반그룹
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 1) {
                    System.out.println(customers[i].toString());
                }
            }
        }//사용하지 않는다.
    }

    public void printVIPGroupByClassifiedCustomersList(Customer[] customers) { // VIP 고객 출력
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 2) {
                    System.out.println(customers[i].toString());
                }
            }
        }//사용하지 않는다.
    }

    public void printVVIPGroupByClassifiedCustomersList(Customer[] customers) { //VVIP그룹 고객 리스트 출력
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 3) {
                    System.out.println(customers[i].toString());
                }
            }
        }//사용하지 않는다.
    }


    public void initCustomersGroup() { //고객 등급 초기화
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
            System.out.println("고객 등급 분류에 실패하였습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customers)) return false;
        Customers customers1 = (Customers) o;
        return Arrays.equals(customers, customers1.customers);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(customers);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customers=" + Arrays.toString(customers) +
                ", customerCount=" + customerCount +
                '}';
    }


    public void groupingByGroup(Groups groups) {
        Customer[][] classifiedTemp = new Customer[groups.getGroups().length][1];
        for (int i = 0; i < 4; i++) {
            Customer[] temp1 = new Customer[customerCount];
            int count = 0;
            for (int j = 0; j < customerCount; j++) {
                if (customers[j] != null) {
                    if (customers[j].getGroup().getGroupType().ordinal() == i) {
                        temp1[count] = customers[j];
                        count++;
                    }
                }
            }
            Customer[] temp2 = Arrays.copyOf(temp1, count);
            classifiedTemp[i] = temp2;
        }
        classifiedCustomers.setClassifiedCustomers(classifiedTemp);
    }
}


