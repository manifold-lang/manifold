package org.manifold.compiler.front;

import org.manifold.compiler.TypeValue;
import org.manifold.compiler.UndefinedBehaviourError;
import org.manifold.compiler.Value;

public class VariableReferenceExpression extends Expression {
  private final VariableIdentifier variable;
  
  public VariableIdentifier getIdentifier() {
    return variable;
  }

  public VariableReferenceExpression(VariableIdentifier variable) {
    this.variable = variable;
  }

  @Override
  public TypeValue getType(Scope scope) {
    return null;
    //return variable.getType();
  }

  @Override
  public Value getValue(Scope scope) {
    // TODO better error handling
    try {
      return scope.getVariableValue(variable);
    } catch (VariableNotAssignedException e) {
      throw new UndefinedBehaviourError(
          "reference to unassigned variable '" + variable.toString() + "'");
    } catch (VariableNotDefinedException e) {
      throw new UndefinedBehaviourError(
          "reference to undefined variable '" + variable.toString() + "'");
    }
  }

  @Override
  public void verify(Scope scope) throws Exception {
    // variable.verify();
    // if (!variable.isAssigned()) {
    //   throw new VariableNotAssignedException(variable);
    // }
  }

  @Override
  public boolean isAssignable() {
    return true;
  }

  @Override
  public boolean isElaborationtimeKnowable(Scope scope) {
    try {
      return scope.getVariable(variable).getValue().isElaborationtimeKnowable();
    } catch (VariableNotDefinedException ex) {
      assert(false);
      return false;
    }
  }

  @Override
  public boolean isRuntimeKnowable(Scope scope) {
    try {
      return scope.getVariable(variable).getValue().isRuntimeKnowable();
    } catch (VariableNotDefinedException ex) {
      assert(false);
      return false;
    }
  }

  @Override
  public void accept(ExpressionVisitor visitor) {
    visitor.visit(this);
  }
  
}
