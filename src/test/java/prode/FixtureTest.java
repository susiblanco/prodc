package prode;

import prode.Fixture;

import org.javalite.activejdbc.ValidatorAdapter;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomValidator extends ValidatorAdapter{
   void validate(Fixture fx){
       boolean valid = true;
       if(!valid)

         m.addValidator(this, "custom_error");
    }
}

public class FixtureTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("FixtureTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("FixtureTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  @Test
  public void validatePrecenseOfFixtureDescription(){
      Fixture fixture = new Fixture();
      fixture.set("description", null);
      assertEquals(fixture.isValid(), false);
  }
}