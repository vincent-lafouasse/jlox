package com.poss.lox;

import java.util.List;

abstract class Expr {
  static class Binary extends Expr {
    Binary(Expr left, Token operator) {
      this.left = left;
      this.operator = operator;
    }

    final Expr left;
    final Token operator;
  }
  static class Grouping extends Expr {
    Grouping(Expr left, Token operator) {
      this.left = left;
      this.operator = operator;
    }

    final Expr left;
    final Token operator;
  }
  static class Literal extends Expr {
    Literal(Expr left, Token operator) {
      this.left = left;
      this.operator = operator;
    }

    final Expr left;
    final Token operator;
  }
  static class Unary extends Expr {
    Unary(Expr left, Token operator) {
      this.left = left;
      this.operator = operator;
    }

    final Expr left;
    final Token operator;
  }
}
