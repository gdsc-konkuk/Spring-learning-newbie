package gdsc.shine.springlearningsimple.bean.context;

public class Shine {
    String name;
    Printer printer;

    public Shine() {
    }

    public Shine(String name, Printer printer) {
        this.name = name;
        this.printer = printer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public String sayHello() {
        return "Hello " + this.name;
    }

    public void print() {
        this.printer.print(sayHello());
    }
}
