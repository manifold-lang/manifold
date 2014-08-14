package org.manifold.compiler;

public class StringTypeValue extends TypeValue {

  private static final StringTypeValue instance = new StringTypeValue();

  private StringTypeValue() { }

  public static StringTypeValue getInstance() {
    return instance;
  }
  
  @Override
  public void accept(ValueVisitor visitor) {
    visitor.visit(this);
  }
  
}
