package org.manifold.compiler;

import java.util.Map;

public class ConstraintValue extends Value {

  private final Attributes attributes;

  public Value getAttribute(String attrName) throws
      UndeclaredAttributeException {
    return attributes.get(attrName);
  }

  public ConstraintValue(ConstraintType type, Map<String, Value> attrs)
      throws UndeclaredAttributeException, InvalidAttributeException,
      TypeMismatchException {
    super(type);
    this.attributes = new Attributes(type.getAttributes(), attrs);
  }

  @Override
  public boolean isElaborationtimeKnowable() {
    return false;
  }

  @Override
  public boolean isRuntimeKnowable() {
    return true;
  }
  
  @Override
  public void accept(ValueVisitor visitor) {
    visitor.visit(this);
  }
  
}
