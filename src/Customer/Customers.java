package Customer;

public class Customers {
    static Customers customers;

    private final Customer[] customersList;


    private  int customerCount = 0;


    public Customer[] getCustomers() {
        return customersList;
    }

    public int getCustomerCount() {
        return customerCount;
    }


    private Customers() {
        customersList = new Customer[1000];
    }

    public static Customers getInstance(){
        if(customers==null){
            customers = new Customers();
        }
        return customers;
    }


    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }




    public boolean deleteCustomerById(String id) {
        for (int i = 0; i < this.customerCount; i++) {
            if (customersList[i].getId().equals(id)) {
                for (int j = i; j < customerCount - 1; j++) {
                    customersList[j] = customersList[j + 1];
                }
                customerCount--;
                return true;
            }
            else{
                System.out.println("해당 아이디가 존재하지 않습니다.");
            }
        }
        return false;
    }
    public boolean updateCustomerById(String id,String name,String customerId,int spendHour,int spendMoney) {
        int index = 0;
        for(int i=0; i<this.customerCount;i++){
            if(customersList[i].getId().equals(id)){
                index = i;
                break;
            }
            else{
                System.out.println("입력 id를 확인해주세요.");
                return false;
            }
        }
        Customer customer =getById(id);
        if(customer!=null){
        customer.setName(name);
        customer.setCustomerId(customerId);
        customer.setSpendMoney(spendMoney);
        customer.setUseHour(spendHour);
        if(validation(customer)){
        customersList[index] = customer;
        return true;}}
        System.out.println("입력값을 확인해주세요.");
        return false;
    }

    public Customer getById(String id) {
        for (int i = 0; i < customerCount; i++) {
            if(customersList[i]!=null) {
                if (customersList[i].getId().equals(id)) {
                    return customersList[i];
                }
            }
        }
        return null;
    }

    public boolean addCustomer(Customer customer) {
        if(validation(customer)) {
            if(customerCount<1000) {
                customersList[customerCount] = customer;
                customerCount++;
                return true;
            }
            else{
                System.out.println("고객이 가득 찼습니다.");
                return false;
            }

        }
        else{
            System.out.println("입력값을 확인해주세요.");
            return false;
        }
    }

    private boolean validation(Customer customer){
        String customerId = customer.getCustomerId();
        String customerName = customer.getName();
        if(customerId.length()<5||customerId.length()>12){
            System.out.println("아이디는 5자 이상 12자 이하로 입력해주세요.");
            return false;
        }
        if(customerName.length()<2||customerName.length()>5){
            System.out.println("이름은 3자 이상 입력해주세요.");
            return false;
        }
        if(!customerId.matches("^[a-zA-Z]*$")){
            System.out.println("아이디는 영어로 3글자 이상 입력해주세요.");
            return false;
        }
        return true; //검증 부분 수정 필요함
    }

}
