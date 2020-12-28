package studsluzba.coders;

import java.util.List;


public abstract class Coder<T extends AbstractCode> {


    protected List<T> codes;

    public String getValueForCode(String code) {
        for (T coderItem : codes) {
            if (coderItem.getCode().equals(code))
                return coderItem.getValue();
        }
        return null;
    }

    abstract protected void loadCodes();

    public List<T> getCodes() {
        return codes;
    }

}
