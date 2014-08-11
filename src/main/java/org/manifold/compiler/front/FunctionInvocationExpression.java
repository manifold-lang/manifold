package org.manifold.compiler.front;

import org.manifold.compiler.TypeValue;
import org.manifold.compiler.Value;

public class FunctionInvocationExpression extends Expression {
  private Expression functionExpression;
  public Expression getFunctionExpression() {
    return functionExpression;
  }
  
  private Expression inputExpression;
  public Expression getInputExpression() {
    return inputExpression;
  }

  public FunctionInvocationExpression(
      Expression functionExpression,
      Expression inputExpression) {
    this.functionExpression = functionExpression;
    this.inputExpression = inputExpression;
  }
  
  private FunctionValue getFunctionValue(Scope scope) {
    return functionExpression.evaluate(scope, FunctionValue.class);
  }
  
  private FunctionTypeValue getFunctionTypeValue(Scope scope) {
    FunctionValue fVal = getFunctionValue(scope);
    return (FunctionTypeValue) fVal.getType();
  }
  
  @Override
  public TypeValue getType(Scope scope) {
    return getFunctionTypeValue(scope).getOutputType();
  }

  @Override
  public Value getValue(Scope scope) {
    assert(false);
    return null;
  }

  @Override
  public void verify(Scope scope) throws Exception{
    functionExpression.verify(scope);
    inputExpression.verify(scope);
    
    TypeValue functionInputType = getFunctionTypeValue(scope).getInputType();
    TypeValue inputType = inputExpression.getType(scope);
    
    assert(inputType.isSubtypeOf(functionInputType));
  }

  @Override
  public boolean isAssignable() {
    return false;
  }

  @Override
  public boolean isElaborationtimeKnowable(Scope scope) {
    return false;
  }

  @Override
  public boolean isRuntimeKnowable(Scope scope) {
    return true;
  }
  
  @Override
  public void accept(ExpressionVisitor visitor) {
    visitor.visit(this);
  }

}
