<div class="container">
	<div id="insertion-form" class="row main">
		<div class="main-login main-center">
			<h5>Create Your New Insertion.</h5>
			<form class="" method="post" action="#">

				<div class="form-group">
					<label for="sale_type" class="cols-sm-2 control-label">Select
						Sale Type</label>
					<div class="cols-sm-10">
						<select id="sale_type" class="selectpicker show-tick"
							data-style="btn-success" data-width="50%">
							<option id="compraora">Buy Now</option>
							<option id="asta">Auction</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="cols-sm-2 control-label">Insertion
						Title</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user fa"
								aria-hidden="true"></i></span> <input type="text" class="form-control"
								name="name" id="insertion-title"
								placeholder="Enter Insertion Title" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="category" class="cols-sm-2 control-label">Select
						Category</label>
					<div class="cols-sm-10">
						<div class="form-group">
							<%@ include file="category.html"%>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="Price" class="cols-sm-2 control-label">Insertion
						Price</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-money fa"
								aria-hidden="true"></i></span> <input type="text" class="form-control"
								name="price" id="insertion-price" placeholder="Enter price" />
						</div>
					</div>
				</div>

				<div id="amountDiv" class="form-group">
					<label for="username" class="cols-sm-2 control-label">Quantity</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-cubes fa"
								aria-hidden="true"></i></span> <input type="text" class="form-control"
								name="quantity" id="insertion-quantity"
								placeholder="Enter Quantity" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="description" class="cols-sm-2 control-label">Description</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="fa fa-info-circle fa-lg" aria-hidden="true"></i></span>
							<textarea class="form-control" rows="5"
								id="insertion-description"></textarea>
						</div>
					</div>
				</div>

				<div id="date-picker" class="date form-group hidden">
				<label for="date" class="cols-sm-2 control-label">Insert Expiration Date</label>
					<div class="input-group input-append date" id="datePicker">
						<span class="input-group-addon add-on"><i
							class="fa fa-calendar fa"></i></span>
						<input id="expiration-date" type="text" class="form-control" name="date" placeholder="Select Expiration date" />
					</div>

				</div>


				<div class="form-group ">
					<a type="button" id="next-button"
						class="btn btn-success btn-lg btn-block ">Next<span> <i
							class="fa fa-arrow-right fa-lg" aria-hidden="true"></i></span></a>
				</div>

			</form>
		</div>
	</div>

	<div id="upload-image" class="row main hidden">
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Upload Files</strong> <small>Bootstrap files upload</small>
			</div>
			<div class="panel-body">

				<!-- Standar Form -->
				<h4>Select files from your computer</h4>
				<form method="post" enctype="multipart/form-data"
					id="js-upload-form" action="ServletUpload">
					<div class="form-inline">
						<div class="form-group">
							<input id="input_id_insertion" type="number" class="hidden" name="nome">
							<input type="file" name="files[]" id="js-upload-files" multiple>
						</div>
						<button type="submit" class="btn btn-sm btn-primary"
							id="js-upload-submit">Upload files</button>
					</div>
				</form>

				<!-- Progress Bar 
          <div class="progress" style="margin-top: 10px;">
            <div class="progress-bar" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
              <span class="sr-only">60% Complete</span>
            </div>
          </div> 
-->
				<!-- Upload Finished -->
				<div class="js-upload-finished">
					<h3>Processed files</h3>
					<div id="processed-files" class="list-group">
						<!-- <a href="#" class="list-group-item list-group-item-success"><span class="badge alert-success pull-right">Success</span>image-01.jpg</a>
              <a href="#" class="list-group-item list-group-item-success"><span class="badge alert-success pull-right">Success</span>image-02.jpg</a>  -->
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			+function($) {
				'use strict';

				// UPLOAD CLASS DEFINITION
				// ======================

				var dropZone = document.getElementById('drop-zone');
				var uploadForm = document.getElementById('js-upload-form');

				var startUpload = function(files) {
					console.log(files)

					for (var i = 0; i < files.length; i++) {

					}

				}

				/*    uploadForm.addEventListener('submit', function(e) {
				        var uploadFiles = document.getElementById('js-upload-files').files;
				        e.preventDefault()

				        startUpload(uploadFiles)
				    })*/

				/*  	    dropZone.ondrop = function(e) {
				  	        e.preventDefault();
				  	        this.className = 'upload-drop-zone';

				  	        startUpload(e.dataTransfer.files)
				  	    }

				  	    dropZone.ondragover = function() {
				  	        this.className = 'upload-drop-zone drop';
				  	        return false;
				  	    }

				  	    dropZone.ondragleave = function() {
				  	        this.className = 'upload-drop-zone';
				  	        return false;
				  	    }*/

			}(jQuery);
		</script>
	</div>
	<!-- /container -->

</div>