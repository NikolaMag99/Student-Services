package studsluzba.coders;

public class SimpleCodeValue extends AbstractCode {


    public SimpleCodeValue(String code) {
        super(code);
    }

    private String value;

    @Override
    public String getValue() {
        return this.value;
    }

}
