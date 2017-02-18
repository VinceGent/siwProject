<%@page import="elements.Insertion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/scriptPayment.js"></script>
<link href="css/stylePayment.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="scripts/guiScript.js"></script>
<script type="text/javascript" src="scripts/logicScript.js"></script>
<!-- If you're using Stripe for payments -->
<script type="text/javascript" src="https://js.stripe.com/v2/"></script>
</head>
<body>
	<%
		Insertion insertion = (Insertion) request.getSession().getAttribute("insertion");
		float total = 0;
		if (insertion == null) {
			total = Float.parseFloat(request.getSession().getAttribute("total").toString());
		} else
			total = insertion.getPrice();
	%>
	<div class="container">
		<div id="Checkout" class="inline">
			<h1>Pay Invoice</h1>
			<div class="card-row">
				<span class="visa"></span> <span class="mastercard"></span> <span
					class="amex"></span> <span class="discover"></span>
			</div>
			<form>
				<div class="form-group">
					<label for="PaymentAmount">Payment amount</label>
					<div class="amount-placeholder">
						<span></span> 
						<span> <%out.print(total);%>   &euro;
						</span> 
					</div>
				</div>
				<div class="form-group">
					<label or="NameOnCard">Name on card</label> <input id="NameOnCard"
						class="form-control" type="text" maxlength="255"></input>
				</div>
				<div class="form-group">
					<label for="CreditCardNumber">Card number</label> <input
						id="CreditCardNumber" class="null card-image form-control"
						type="text"></input>
				</div>
				<div class="expiry-date-group form-group">
					<label for="ExpiryDate">Expiry date</label> <input id="ExpiryDate"
						class="form-control" type="text" placeholder="MM / YY"
						maxlength="7"></input>
				</div>
				<div class="security-code-group form-group">
					<label for="SecurityCode">Security code</label>
					<div class="input-container">
						<input id="SecurityCode" class="form-control" type="text"></input>
						<i id="cvc" class="fa fa-question-circle"></i>
					</div>
					<div class="cvc-preview-container two-card hide">
						<div class="amex-cvc-preview"></div>
						<div class="visa-mc-dis-cvc-preview"></div>
					</div>
				</div>
				<div class="zip-code-group form-group">
					<label for="ZIPCode">ZIP/Postal code</label>
					<div class="input-container">
						<input id="ZIPCode" class="form-control" type="text"
							maxlength="10"></input> <a tabindex="0" role="button"
							data-toggle="popover" data-trigger="focus" data-placement="left"
							data-content="Enter the ZIP/Postal code for your credit card billing address."><i
							class="fa fa-question-circle"></i></a>
					</div>
				</div>
				<button
					id="<%out.print(request.getSession().getAttribute("id_item").toString());%>"
					class="btn btn-block btn-success submit-button payButton"
					type="button">
					<span class="submit-button-lock"></span> <span class="align-middle">Pay
					</span>
				</button>
			</form>
		</div>
	</div>

</body>
</html>