package org.manifold.compiler;

public class IntegerTypeValue extends TypeValue {
  private static final IntegerTypeValue instance = new IntegerTypeValue();

  private IntegerTypeValue() {

  }

  public static IntegerTypeValue getInstance() {
    return instance;
  }
  
  @Override
  public void accept(ValueVisitor visitor) {
    visitor.visit(this);
  }
  
}
