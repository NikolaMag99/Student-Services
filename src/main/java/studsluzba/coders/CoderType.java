package studsluzba.coders;

public enum CoderType {

    MESTO(TextFileSimpleCoder.class, "coders/mesta"),
    SMER(TextFileSimpleCoder.class, "coders/smer"),
    DRZAVA(TextFileSimpleCoder.class, "coders/drzave"),
    TIP_SREDNJE_SKOLE(TextFileSimpleCoder.class, "coders/tipSrednjeSkole"),
    VISOKOSKOLSKA_USTANOVA;

    private CoderType(Class<? extends Coder<? extends AbstractCode>> klasa, String path) {
        this.tip = klasa;
        this.path = path;
    }

    private CoderType() {

    }

    public Class<? extends Coder<? extends AbstractCode>> getTip() {
        return tip;
    }

    public void setTip(Class<? extends Coder<? extends AbstractCode>> tip) {
        this.tip = tip;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private Class<? extends Coder<? extends AbstractCode>> tip;
    private String path;


}
