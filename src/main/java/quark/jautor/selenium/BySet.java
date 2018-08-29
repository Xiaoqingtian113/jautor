package quark.jautor.selenium;

import org.openqa.selenium.By;

public class BySet {

	public static By getBy(String locateType, String Expression) {
		By by = null;
		switch (locateType) {
			case "id":
				by = By.id(Expression);
				break;
			case "xpath":
				by = By.xpath(Expression);
				break;
			case "name":
				by = By.name(Expression);
				break;
			case "tagName":
				by = By.tagName(Expression);
				break;
			case "partialLinkText":
				by = By.partialLinkText(Expression);
				break;
			case "linkText":
				by = By.linkText(Expression);
				break;
			case "className":
				by = By.className(Expression);
				break;
			case "cssSelector":
				by = By.cssSelector(Expression);
				break;
			default:
				by = null;
				break;
		}
		return by;
	}
}
