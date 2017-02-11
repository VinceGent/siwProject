
<div class="container well describe">
	<div class="row">
		<div class="col-md-2">
			<label>Categoria:</label>
		</div>
		<div class="col-md-10">
			<div class="dropdown form-group">
				<%@ include file="category.html"%>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<label>Titolo:</label>
		</div>
		<div class="col-md-3  form-group">
			<input id="title" type="text" class="form-control"
				placeholder="Inserisci titolo">
		</div>
	</div>

	<div class="row">
		<div class="col-md-2">
			<label>Prezzo:</label>
		</div>
		<div class="col-md-10 form-group">
			<input id="price" type="number" name="quantity" STEP="0.01" min="0"
				placeholder="Inserisci prezzo"></input>
		</div>
	</div>
	<div class="row" id="row-quantity">
		<div class="col-md-2">
			<label>Quantità:</label>
		</div>
		<div class="col-md-10 form-group">
			<input id="amount" type="number" name="quantity" min="1"
				placeholder="Inserisci quantità"></input>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2 ">
			<label>Descrizione:</label>
		</div>
		<div class="col-md-10 form-group">
			<textarea id="description" rows="6" cols="50" name="comment"></textarea>
		</div>
	</div>


	<div class="row">
		<div class="col-md-2 ">
			<label>Data scadenza:</label>
		</div>


		<div class="col-md-3 date form-group">
			<div class="input-group input-append date" id="datePicker">
				<input id="date" type="text" class="form-control" name="date" /> <span
					class="input-group-addon add-on"><span
					class="glyphicon glyphicon-calendar"></span></span>
			</div>

		</div>

	</div>

	<div class="row">
		<div class="col-md-2">
			<label>Tipo vendita</label>
		</div>
		<div class="col-md-10">
			<div class="dropdown form-group">
				<select id="seller_type">
					<option>compraora</option>
					<option>asta</option>
				</select>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<label>Inserisci immagini:</label>
		</div>
		<div class=" col-md-10 form-group">
			<input type="file" name="pic" accept="image/*"> <input
				type="submit">
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<div class=" col-md-6" style="float: right;">
				<button id="send_insertion" type="submit" class="btn btn-default">Invia
					inserzione</button>
			</div>
		</div>
	</div>

</div>
