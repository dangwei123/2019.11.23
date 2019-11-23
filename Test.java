public class Book {
    public String name;
    public String author;
    public int price;
    public String type;
    public boolean isBorrowed;
    public Book(String name,String author,int price,String type){
        this.name=name;
        this.author=author;
        this.price=price;
        this.type=type;
    }
    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ((isBorrowed) ? "，已经被借出":"，未借出")+
                '}';
    }
	
	public class BookList {
    public Book[] books=new Book[10];
    public int size;
    public BookList(){
        books[0]=new Book("三国演义","罗贯中",12,"小说");
        books[1]=new Book("水浒传","施耐庵",13,"小说");
        books[2]=new Book("西游记","吴承恩",14,"小说");
        this.size=3;
    }

    public void setBooks(int pos,Book book){
       this.books[pos]=book;
    }
    public Book getBooks(int pos){
        return books[pos];
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}


public class AddOperation implements IOperation{

    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要添加的书籍的名字：");
        String name=sc.nextLine();
        System.out.println("请输入要添加的书籍的作者：");
        String author=sc.nextLine();
        System.out.println("请输入要添加的书籍的价格：");
        int price=sc.nextInt();
        System.out.println("请输入要添加的书籍的类型：");
        String type=sc.next();

        Book book=new Book(name,author,price,type);
        int size=bookList.getSize();
        bookList.setBooks(size,book);
        bookList.setSize(size+1);
        System.out.println("添加成功");
    }
}


public class BorrowOperation implements IOperation{
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要添加的书籍的名字：");
        String name=sc.nextLine();
        int i=0;
        for(;i<bookList.getSize();i++){
            if(bookList.getBooks(i).name.equals(name)){
                break;
            }
        }
        if(i==bookList.getSize()){
            System.out.println("没有此书");
        }else if(bookList.getBooks(i).isBorrowed){
            System.out.println("此书已经被借阅");
        }else{
            bookList.getBooks(i).isBorrowed=true;
            System.out.println("借阅成功");
        }
    }
}


public class DelOperation implements IOperation{
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要删除的书籍的名字：");
        String name=sc.nextLine();
        int i=0;
        for(;i<bookList.getSize();i++){
            if(bookList.getBooks(i).name.equals(name)){
                break;
            }
        }
        if(i==bookList.getSize()){
            System.out.println("没有此书");
            return;
        }
        for(;i<bookList.getSize()-1;i++){
            Book book=bookList.getBooks(i+1);
            bookList.setBooks(i,book);
        }
        int size=bookList.getSize();
        bookList.setSize(size-1);
        System.out.println("删除成功");
    }
}


public class DisplayOperation implements IOperation{

    @Override
    public void work(BookList bookList) {
        for(int i=0;i<bookList.getSize();i++){
            System.out.println(bookList.getBooks(i));
        }
    }
}

public class ExitOperation implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("退出程序");
        System.exit(0);
    }
}

public class FindOperation implements IOperation{

    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要查找的书籍的名字：");
        String name=sc.nextLine();
        int i=0;
        for(;i<bookList.getSize();i++){
            if(bookList.getBooks(i).name.equals(name)){
                break;
            }
        }
        if(i==bookList.getSize()){
            System.out.println("没有此书");
        }else{
            System.out.println("找到了");
        }
    }
}


public interface IOperation {
    Scanner sc=new Scanner(System.in);

     void work(BookList bookList);
}


public class ReturnOperation implements IOperation{
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要归还的书籍的名字：");
        String name=sc.nextLine();
        int i=0;
        for(;i<bookList.getSize();i++){
            if(bookList.getBooks(i).name.equals(name)){
                break;
            }
        }
        if(i==bookList.getSize()){
            System.out.println("没有此书");
        }else{
            bookList.getBooks(i).isBorrowed=false;
            System.out.println("归还成功");
        }
    }
}


public abstract class User {
    protected String name;
    protected IOperation[] operations;

    public abstract int menu();

    public void doOperations(int choice,BookList bookList){
        operations[choice].work(bookList);
    }
}

public class Admi extends User{
    public Admi(String name){
        this.name=name;
        this.operations=new IOperation[]{
                new ExitOperation(),
                new AddOperation(),
                new DelOperation(),
                new FindOperation(),
                new DisplayOperation(),
        };
    }
    @Override
    public int menu() {
        System.out.println("=========================================");
        System.out.println("Hello,"+this.name+" 欢迎来到图书管理系统");
        System.out.println("1.添加书籍");
        System.out.println("2.删除书籍");
        System.out.println("3.查找书籍");
        System.out.println("4.打印书籍");
        System.out.println("0.退出程序");
        System.out.println("=========================================");
        System.out.println("请输入您的选择：");
        Scanner sc=new Scanner(System.in);
        return sc.nextInt();
    }
}


public class Normal extends User {
    public Normal(String name){
        this.name=name;
        this.operations=new IOperation[]{
                new ExitOperation(),
                new BorrowOperation(),
                new FindOperation(),
                new ReturnOperation(),
                new DisplayOperation(),
        };
    }
    @Override
    public int menu() {
        System.out.println("=========================================");
        System.out.println("Hello,"+this.name+" 欢迎来到图书管理系统");
        System.out.println("1.借阅书籍");
        System.out.println("2.查找书籍");
        System.out.println("3.归还书籍");
        System.out.println("4.打印书籍");
        System.out.println("0.退出程序");
        System.out.println("=========================================");
        System.out.println("请输入您的选择：");
        Scanner sc=new Scanner(System.in);
        return sc.nextInt();
    }
}


public class Main{
    public static User login(){

        System.out.println("请输入您的名字：");
        Scanner sc=new Scanner(System.in);
        String name=sc.nextLine();
        System.out.println("请输入您的身份(1-->管理者  2-->学生)：");
        int choice=sc.nextInt();
        if(choice==1){
            return new Admi(name);
        }else{
            return new Normal(name);
        }
    }

    public static void main(String[] args) {
        BookList bookList=new BookList();
        User user=login();
        while(true){
            int choice=user.menu();
            user.doOperations(choice,bookList);
        }
    }
}
