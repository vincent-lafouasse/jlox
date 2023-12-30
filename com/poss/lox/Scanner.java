package com.poss.lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Scanner {
	private final String source;
	private final List<Token> tokens = new ArrayList<>();
	private int start = 0;
	private int current = 0;
	private int line = 1;

	Scanner(String source) {
		this.source = source;
	}

	List<Token> scanTokens() {
		while (!isAtEnd()) {
			start = current;
			scanToken();
		}

		tokens.add(new Token(TokenType.EOF, "", null, line));
		return tokens;
	}

	private void scanToken() {
		char c = advance();

		switch (c) {
			// scan single char tokens
			case '(': addToken(TokenType.LEFT_PAREN); break;
			case ')': addToken(TokenType.RIGHT_PAREN); break;
			case '{': addToken(TokenType.LEFT_BRACE); break;
			case '}': addToken(TokenType.RIGHT_BRACE); break;
			case ',': addToken(TokenType.COMMA); break;
			case '.': addToken(TokenType.DOT); break;
			case '-': addToken(TokenType.MINUS); break;
			case '+': addToken(TokenType.PLUS); break;
			case ';': addToken(TokenType.SEMICOLON); break;
			case '*': addToken(TokenType.STAR); break;

			// scan double char tokens
			case '!':
				addToken(match('=') ? TokenType.BANG_EQUAL
									: TokenType.BANG);
				break;
			case '=':
				addToken(match('=') ? TokenType.EQUAL_EQUAL
									: TokenType.EQUAL);
				break;
			case '<':
				addToken(match('=') ? TokenType.LESS_EQUAL
									: TokenType.LESS);
				break;
			case '>':
				addToken(match('=') ? TokenType.GREATER_EQUAL
									: TokenType.GREATER);
				break;

			// ignore comments
			case '/':
				if (match('/')) {
					while (peek() != '\n' && !isAtEnd())
						advance();
				} else {
					addToken(TokenType.SLASH);
				}
				break;

			// ignore whitespace
			case ' ':
			case '\r':
			case '\t':
				break;
			case '\n':
				line++;
				break;

			case '"': scanString(); break;

			default:
				Lox.error(line, "Unexpected character.");
		}
	}

	private void scanString() {

	}

	private void addToken(TokenType type) {
		addToken(type, null);
	}

	private void addToken(TokenType type, Object literal) {
		String lexeme = source.substring(start, current);
		tokens.add(new Token(type, lexeme, literal, line));
	}

	private char advance() {
		return source.charAt(current++);
	}

	private boolean match(char expected) {
		if (isAtEnd()) return false;
		if (peek() != expected) return false;
		current++;
		return true;
	}

	private char peek() {
		if (isAtEnd()) return '\0';
		return source.charAt(current);
	}

	private boolean isAtEnd() {
		return current >= source.length();
	}
}
