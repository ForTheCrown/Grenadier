package net.forthecrown.grenadier.annotations.tree;

import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public interface RequiresTree extends Tree {

  record MemberRequires(int tokenStart, MemberChainTree ref)
      implements RequiresTree
  {

    @Override
    public <R, C> R accept(TreeVisitor<R, C> visitor, C context) {
      return visitor.visitMemberRequirement(this, context);
    }
  }

  record PermissionRequires(int tokenStart, Name name)
      implements RequiresTree
  {

    @Override
    public <R, C> R accept(TreeVisitor<R, C> visitor, C context) {
      return visitor.visitPermissionRequirement(this, context);
    }
  }

  record VariableRequires(int tokenStart, String variable)
      implements RequiresTree, VariableHolder
  {

    @Override
    public <R, C> R accept(TreeVisitor<R, C> visitor, C context) {
      return visitor.visitVariableRequires(this, context);
    }
  }

  record ConstantRequires(int tokenStart, boolean value)
      implements RequiresTree
  {

    @Override
    public <R, C> R accept(TreeVisitor<R, C> visitor, C context) {
      return visitor.visitConstantRequires(this, context);
    }
  }
}