package net.forthecrown.grenadier.annotations.tree;

import java.util.Map;
import net.forthecrown.grenadier.annotations.Token;
import net.forthecrown.grenadier.annotations.util.Result;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public interface ArgumentTypeTree extends Tree {

  record TypeInfoTree(int tokenStart, String name, Map<String, Token> options)
      implements ArgumentTypeTree
  {

    public Result<Token> getOption(String optionName) {
      Token value = options.get(optionName);

      if (value == null) {
        return Result.fail("Missing '%s' option from '%s' argument type info",
            optionName, name
        );
      }

      return Result.success(value);
    }

    @Override
    public <R, C> R accept(TreeVisitor<R, C> visitor, C context) {
      return visitor.visitArgumentTypeTree(this, context);
    }
  }

  record VariableTypeReference(int tokenStart, String variable)
      implements ArgumentTypeTree, VariableHolder
  {

    @Override
    public <R, C> R accept(TreeVisitor<R, C> visitor, C context) {
      return visitor.visitVariableArgumentType(this, context);
    }
  }
}