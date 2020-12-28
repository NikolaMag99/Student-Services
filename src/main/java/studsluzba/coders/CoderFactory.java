package studsluzba.coders;

import org.springframework.stereotype.Component;


@Component
public class CoderFactory {


    private Coder<SimpleCode> drzaveCoder;
    private Coder<SimpleCode> mestaCoder;
    private Coder<SimpleCode> tipSrednjeSkole;
    private Coder<SimpleCode> smerCoder;


    // ucita samo ako nije ucitano
    public Coder<SimpleCode> getSimpleCoder(CoderType coderType) {

        switch (coderType) {

            case DRZAVA:
                if (drzaveCoder == null)
                    drzaveCoder = new TextFileSimpleCoder(coderType.getPath());
                return drzaveCoder;
            case MESTO:
                if (mestaCoder == null)
                    mestaCoder = new TextFileSimpleCoder(coderType.getPath());
                return mestaCoder;
            case TIP_SREDNJE_SKOLE:
                if (tipSrednjeSkole == null)
                    tipSrednjeSkole = new TextFileSimpleCoder(coderType.getPath());
                return tipSrednjeSkole;
            case SMER:
                if (smerCoder == null)
                    smerCoder = new TextFileSimpleCoder(coderType.getPath());
                return smerCoder;
            default:
                return null;
        }
		/*
		 * 
		 * 
		Class<?> c;
		try {
			c = Class.forName(coderType.getClass().getName());
			Constructor<?> cons = c.getConstructor(String.class);
			Coder<? extends AbstractCode> object = (Coder<? extends AbstractCode>)cons.newInstance(coderType.getPath());		
			return object;
		} catch (Exception e) {		
			e.printStackTrace();
			return null;
		}
		*/
    }


}
