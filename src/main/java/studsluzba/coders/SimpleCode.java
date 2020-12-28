package studsluzba.coders;


public class SimpleCode extends AbstractCode {


    public SimpleCode(String code) {
        super(code);
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public String toString() {
        return getCode();
    }


}
