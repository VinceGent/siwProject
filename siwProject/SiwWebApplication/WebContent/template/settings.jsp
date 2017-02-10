<form class="form-horizontal">
	<fieldset class="fieldset">
		<!-- Form Name -->
		<div class="box-title main-title">
			<h2 style="text-align: center;">Account Settings</h2>
		</div>
		<!-- Text input Username -->
		<div class="form-group" style="margin-top: 3%;">
			<label class="col-md-4 control-label" for="username-field">Username</label>
			<div class="col-md-6">
				<input id="username-field" name="textinput" type="text"
					placeholder="placeholder" class="form-control input-md" required="">
			</div>
		</div>
		<!-- Text input email-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="email-field">Email</label>
			<div class="col-md-6">
				<input id="email-field" name="email" type="text"
					placeholder="insert email here" class="form-control input-md">
			</div>
		</div>
		<!-- Text input Name -->

		<div class="form-group">
			<label class="col-md-4 control-label" for="nome-field">Name</label>
			<div class="col-md-6">
				<input id="name-field" type="text"
					placeholder="nome" class="form-control input-md">
			</div>
		</div>
		<!-- Text input Surname -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="surname-field">Surname</label>
			<div class="col-md-6">
				<input id="surname-field" name="surname-field" type="text"
					placeholder="surname" class="form-control input-md" required="">
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-4">
				<button type="button" class="btn btn-info" style="float: right;"
					data-toggle="collapse" data-target="#passwordChange">Change
					Password</button>
			</div>
			<div id="passwordChange" class="col-md-7 collapse">
				<div class="row" style="margin-bottom: 1%;">
					<label for="pwd" class="control-label col-md-6">Old
						Password</label>
					<div class="col-md-6" style="float: right;">
						<input id="old-password-field" type="password" class="form-control input-md" id="pwd">
					</div>
				</div>
				<div class="row" style="margin-bottom: 1%;">
					<label for="pwd" class="control-label col-md-6">New
						Password</label>
					<div class="col-md-6" style="float: right;">
						<input id="new-password-field" type="password" class="form-control input-md" id="pwd">
					</div>
				</div>
				<div class="row">
					<label for="pwd" class="control-label col-md-6">Confirm New
						Password</label>
					<div class="col-md-6" style="float: right;">
						<input id="repeat-new-password-field" type="password" class="form-control input-md" id="pwd">
					</div>
				</div>
			</div>
		</div>
		
		<!-- Button (Double) -->
		<div class="form-group" style="margin-top: 4%;">
			<label class="col-md-4 control-label"></label>
			<div class="col-md-8">
				<button id="clear-settings" name="cancel" class="btn btn-inverse"
					style="float: right; margin-left: 5px; margin-right: 26%;">Cancel</button>
				<button type="button" id="save-settings" class="btn btn-success" style="float: right;">Save</button>
			</div>
		</div>
	</fieldset>
</form>

