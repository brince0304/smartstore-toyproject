package Menu;

import Customer.Customer;
import Customer.Customers;
import Customer.ClassifiedCustomers;

import java.io.IOException;

public class CustomerMenu extends MenuImpl{


    static CustomerMenu customerMenu;
    private final Customers customers;
    private final ClassifiedCustomers classifiedCustomers;


    public CustomerMenu( Customers customers, ClassifiedCustomers classifiedCustomers) {
        this.customers = customers;
        this.classifiedCustomers = classifiedCustomers;
    }

    public static CustomerMenu getInstance(Customers customers, ClassifiedCustomers classifiedCustomers){
        if(customerMenu == null){
            customerMenu = new CustomerMenu(customers, classifiedCustomers);
        }
        return customerMenu;
    }




   @Override
    public int showMenu() throws IOException {
        System.out.println("고객 관리 메뉴");
        System.out.println("1. 고객 추가");
        System.out.println("2. 고객 삭제");
        System.out.println("3. 고객 수정");
        System.out.println("4. 고객 조회");
        System.out.println("5. 메인 메뉴로 돌아가기");
        System.out.println("메뉴를 선택하세요");
        return Integer.parseInt(inputString());
    }

  @Override
    public void selectMenu(int menu) throws IOException {
        switch (menu){
            case 1:
                addCustomer(addCustomerMenu());
                break;
            case 2:
                deleteCustomer(deleteCustomerMenu());
                break;
            case 3:
                System.out.println("수정을 원하는 고유 번호를 입력해주세요.");
                updateCustomerMenu(inputString());
                break;
            case 4:
System.out.println("조회를 원하는 고유 번호를 입력해주세요.");
                System.out.println(getCustomer(inputString()));
                break;
            case 5:
                break;
        }
    }



    private String deleteCustomerMenu() throws IOException {
        System.out.println("삭제할 고객의 고유번호을 입력하세요");
        return inputString();
    }


    public Customer addCustomerMenu() throws IOException {
        System.out.println("고객 추가 메뉴");
        System.out.println("이름을 입력해주세요");
        String name = inputString();
        System.out.println("아이디를 입력해주세요");
        String id = inputString();
        System.out.println("이용 금액을 입력해주세요");
        int spendMoney = Integer.parseInt(inputString());
        System.out.println("이용 시간을 입력해주세요");
        int spendTime = Integer.parseInt(inputString());
        return Customer.of(name, id, spendMoney, spendTime);
    }

    public Customer getCustomer(String id){
        Customer customer = customers.getById(id);
        if(customer==null){
            System.out.println("잘못된 ID를 입력하셨습니다.");
        }
        return customer;
    }

    public void addCustomer(Customer customer){
        if(customers.addCustomer(customer)){
            System.out.println("등록되었습니다.");
        }
        else{
            System.out.println("실패하였습니다.");
        }
    }

    public void deleteCustomer(String id){
        if(customers.deleteCustomerById(id)){
        System.out.println("삭제되었습니다.");}
        else{
            System.out.println("삭제에 실패하였습니다.");
        }
    }

    public void updateCustomerMenu(String id) throws IOException {
        if(getCustomer(id)==null){
            System.out.println("잘못된 ID를 입력하셨습니다.");
        }
        else {
            System.out.println("고객 수정 메뉴");
            System.out.println("이름을 입력해주세요");
            String name = inputString();
            System.out.println("아이디를 입력해주세요");
            String customerId = inputString();
            System.out.println("이용 금액을 입력해주세요");
            int spendMoney = Integer.parseInt(inputString());
            System.out.println("이용 시간을 입력해주세요");
            int spendHour = Integer.parseInt(inputString());
            if (customers.updateCustomerById(id, name, customerId, spendHour, spendMoney)) {
                System.out.println("수정되었습니다.");
            } else {
                System.out.println("실패하였습니다.");
            }
        }
    }








}
