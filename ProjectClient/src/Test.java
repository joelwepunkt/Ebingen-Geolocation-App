import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ebingengeolocation.entity.*;
import com.ebingengeolocation.session.LocationManager;
import com.ebingengeolocation.session.LocationManagerInterface;
import com.ebingengeolocation.session.NoSuchRowException;
import com.ebingengeolocation.*;

public class Test {
	public final static String MODULE_NAME = "backend1"; // = Name des
	// EJB-Jar-File
	public final static String BEAN_NAME = "LocationManager";
	public final static String INTERFACE_NAME = "com.ebingengeolocation.session.LocationManagerInterface";
	public final static String LOOKUP_NAME = MODULE_NAME + "/" + BEAN_NAME + "!" + INTERFACE_NAME;

// /////////////////////////
// neue Aufruf-Syntax: <jar-file-name>/<bean-name>!<interface-name>
// /////////////////////////

	public static void main(String[] args) throws NamingException, NoSuchRowException {
		InitialContext ctx = null;
		LocationManagerInterface m = null;
		try {
			ctx = new InitialContext();
			m = (LocationManagerInterface) ctx.lookup(LOOKUP_NAME);
// /////////////////////////
			System.out.println("m.getMessage() = " + m.findByPrimaryKey(1));
			//e.printStackTrace();
		}
		finally {}
	}
}
