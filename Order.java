/**
 * Represents an order object for users and employees
 */
public class Order {

	/**
	 * The type of the order.
	 */
	private String orderType;

	/**
	 * The unique identifier of the order.
	 */
	private String orderID;

	/**
	 * The ID of the user associated with the order.
	 */
	private String userId;

	/**
	 * The username of the user associated with the order.
	 */
	private String username;

	/**
	 * The type of card used for the order payment.
	 */
	private String cardType;

	/**
	 * The date of the order.
	 */
	private String orderDate;

	public Order(String orderType, String orderID, String userId, String username, String cardType, String orderDate) {

		this.orderType = orderType;
		this.orderID = orderID;
		this.userId = userId;
		this.username = username;
		this.cardType = cardType;
		this.orderDate = orderDate;

	}

	/**
	 * Retrieves the order ID.
	 *
	 * @return The order ID.
	 */
	public String getOrderID() {
		return orderID;
	}

	/**
	 * Sets the order ID.
	 *
	 * @param orderID The order ID to set.
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	/**
	 * Retrieves the user ID.
	 *
	 * @return The user ID.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID.
	 *
	 * @param userId The user ID to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Retrieves the username.
	 *
	 * @return The username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Retrieves the card type.
	 *
	 * @return The card type.
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * Sets the card type.
	 *
	 * @param cardType The card type to set.
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * Retrieves the order date.
	 *
	 * @return The order date.
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * Sets the order date.
	 *
	 * @param orderDate The order date to set.
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * Retrieves the order type.
	 *
	 * @return The order type.
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * Sets the order type.
	 *
	 * @param orderType The order type to set.
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String toStringRetail() {
		return  "Type: " + orderType + " | " +
				"Card: " + cardType + " | " +
				"Date: " + orderDate;

	}

	public String toStringRepair() {
		return  "Type: " + orderType + " | " +
				"Time: " + orderDate;

	}

}

