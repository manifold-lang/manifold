package org.whdl.intermediate;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class TestConstraint {

  private static final ConstraintType defaultConstraintDefinition = new ConstraintType(new HashMap<String, Type>());
  private static final Type boolType = BooleanType.getInstance();

  @Test
  public void testGetAttribute() throws UndeclaredAttributeException {
    Constraint ept = new Constraint(defaultConstraintDefinition);
    Value v = new BooleanValue(boolType, true);
    ept.setAttribute("v", v);
    Value vActual = ept.getAttribute("v");
    assertEquals(v, vActual);
  }

  @Test(expected = org.whdl.intermediate.UndeclaredAttributeException.class)
  public void testGetAttribute_nonexistent()
 throws UndeclaredAttributeException {
    Constraint ept = new Constraint(defaultConstraintDefinition);
    Value vBogus = ept.getAttribute("bogus");
  }

  @Test
  public void testSetAttribute() {
    Constraint ept = new Constraint(defaultConstraintDefinition);
    Value v = new BooleanValue(boolType, true);
    ept.setAttribute("v", v);
  }

  @Test
  public void testSetAttribute_multiple_set() {
    // setting an Argument twice should just work
    Constraint ept = new Constraint(defaultConstraintDefinition);
    Value v = new BooleanValue(boolType, true);
    ept.setAttribute("v", v);
    Value v2 = new BooleanValue(boolType, false);
    ept.setAttribute("v", v2);
  }

}
