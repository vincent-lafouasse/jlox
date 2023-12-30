package com.poss.lox;

import java.util.List;

abstract class Expr {
	interface Visitor<R> {
		R visitBinaryExpr(Binary expr);
		R visitGroupingExpr(Grouping expr);
		R visitLiteralExpr(Literal expr);
		R visitUnaryExpr(Unary expr);
	}

	static class Binary extends Expr {
		Binary(Expr left, Token operator) {
			this.left = left;
			this.operator = operator;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitBinaryExpr(this);
		}

		final Expr left;
		final Token operator;
	}

	static class Grouping extends Expr {
		Grouping(Expr left, Token operator) {
			this.left = left;
			this.operator = operator;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitGroupingExpr(this);
		}

		final Expr left;
		final Token operator;
	}

	static class Literal extends Expr {
		Literal(Expr left, Token operator) {
			this.left = left;
			this.operator = operator;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitLiteralExpr(this);
		}

		final Expr left;
		final Token operator;
	}

	static class Unary extends Expr {
		Unary(Expr left, Token operator) {
			this.left = left;
			this.operator = operator;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitUnaryExpr(this);
		}

		final Expr left;
		final Token operator;
	}


  abstract <R> R accept(Visitor<R> visitor);
}
