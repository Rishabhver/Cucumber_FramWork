package util;

import org.aeonbits.owner.Mutable;
import org.aeonbits.owner.Config.Sources;


@Sources({ 
	"file:src\\test\\resources\\propertyFiles\\config.properties" // mention the property file name
})
public interface ConfigProperty extends Mutable{
	
	 @Key("baseURI")
     public String getBaseURI();
	 
	 @Key("basePath")
	 public String getBasePath();
     
	 @Key("validSecretKey")
	 public String getValidSecretKey();
	 
	 @Key("inValidSecretKey")
	 public String getInValidSecretKey();
	 
	 @Key("customerApiEndPoint")
	 public String getCustomerApiEndPoint();
	
}
