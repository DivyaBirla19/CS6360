<?php

require_once ("../project1/component.php");
require_once ("../project1/operation.php");

?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <meta name="description" content="Contact List Application">
  <meta name="author" content="Divya">
    <title>Contact List Application</title>
    <!-- Online stylesheet links used  -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- Custom stylesheet -->
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <main>
     <div class="container text-center">
        <h1 class="py-4 text-light rounded"><i class="fas fa-address-book"></i> Contacts</h1>
         <div class="d-flex justify-content-left">
            <form action="index.php" method="post" class="w-100">
              <div class="row pt-2">
                    <div class="col">
                    <?php inputElement("<i class='fas fa-id-badge'></i>","ID", "contact_id",setID()); ?>
                    </div>
                    <div class="col">
                    <?php inputElement("<i class='fas fa-signature'></i>","First Name", "first_name",""); ?>
                    </div>
                <div class="col">
                    <?php inputElement("<i class='fas fa-signature'></i>","Middle Name", "middle_name",""); ?>
                </div>
                <div class="col">
                    <?php inputElement("<i class='fas fa-signature'></i>","Last Name", "last_name",""); ?>
                </div>
                </div>
                <div class="row pt-2">
                    <div class="col">
                    <?php inputElement("<i class='fas fa-birthday-cake'></i>","Birthday: YYYY-MM-DD", "birth_date",""); ?>
                </div>
                <div class="col">
                    <?php inputElement("<i class='fas fa-birthday-cake'></i>","Anniversary: YYYY-MM-DD", "anni_date",""); ?>
                </div>
                </div>
                <div class="row pt-2">
                    <div class="col">
                    <?php inputElement("<i class='fas fa-phone'></i>","Home Phone", "home_phone",""); ?>
                </div>
                 <div class="col">
                    <?php inputElement("<i class='fas fa-phone'></i>","Cell Phone", "cell_phone",""); ?>
                </div>
                </div>
                 <div class="row pt-2">
                <div class="col">
                    <?php inputElement("<i class='fas fa-map-marker-alt'></i>","Home Address", "home_address",""); ?>
                </div>
                <div class="col">
                    <?php inputElement("<i class='fas fa-map-marker-alt'></i>","Home City", "home_city",""); ?>
                </div>
                <div class="col">
                    <?php inputElement("<i class='fas fa-map-marker-alt'></i>","Home State", "home_state",""); ?>
                </div>
                <div class="col">
                    <?php inputElement("<i class='fas fa-map-marker-alt'></i>","Home zip", "home_zip",""); ?>
                </div>
                </div>    
                <div class="row pt-2">
                <div class="col">
                    <?php inputElement("<i class='fas fa-phone'></i>","Work Phone", "work_phone",""); ?>
                </div>
                <div class="col">
                    <?php inputElement("<i class='fas fa-map-marker-alt'></i>","Work Address", "work_address",""); ?>
                </div>
                <div class="col">
                    <?php inputElement("<i class='fas fa-map-marker-alt'></i>","Work City", "work_city",""); ?>
                </div>
                <div class="col">
                    <?php inputElement("<i class='fas fa-map-marker-alt'></i>","Work State", "work_state",""); ?>
                </div>
                <div class="col">
                    <?php inputElement("<i class='fas fa-map-marker-alt'></i>","Work Zip", "work_zip",""); ?>
                </div>
                </div>
                
                
                 <div class="d-flex justify-content-center">
                  <?php buttonElement("btn-create","btn btn-success","<i class='fas fa-plus'></i>","create","data-toggle='tooltip' data-placement='bottom' title='Create'"); ?>
                 <?php buttonElement("btn-read","btn btn-primary","<i class='fab fa-readme'></i>","read","data-toggle='tooltip' data-placement='bottom' title='Read'"); ?>
                 <?php buttonElement("btn-update","btn btn-warning","<i class='fas fa-edit'></i>","update","data-toggle='tooltip' data-placement='bottom' title='Update'"); ?>
                 <?php buttonElement("btn-delete","btn btn-danger","<i class='fas fa-trash-alt'></i>","delete","data-toggle='tooltip' data-placement='bottom' title='Delete'"); ?>
                  <?php deleteBtn();?>
                  
            </div>
             </form>
              </div>
         <!-- Search box. -->
                <div class="d-flex justify-content-left">
               <form action="index.php" method="post" class="w-100"> 
                 <div>
                 <?php inputElement("<i class='fas fa-search'></i>","Search Here", "data_search",""); ?>
                 <?php buttonElement("btn-search","btn btn-success","<i class='fas fa-search'></i>","Esearch","data-toggle='tooltip' data-placement='bottom' title='Search'"); ?>
                 </div>
         
             </form>
             </div>
             
         <!-- Bootstrap table  -->
          <div class="d-flex justify-content-center">
        <div class="d-flex table-data ">
            <table class="table table-striped table-light">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Fname</th>
                        <th>Mname</th>
                        <th>Lname</th>
                        <th>Birthday</th>
                        <th>Anniversary</th>
                        <th>Home Phone</th>
                        <th>Cell Phone</th>
                        <th>Home Address</th>
                        <th>Home City</th>
                        <th>Home State</th>
                        <th>Home Zip</th>
                        <th>Work Phone</th>
                        <th>Work Address</th>
                        <th>Work City</th>
                        <th>Work State</th>
                        <th>Work Zip</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody id="tbody">    
                <?php

                    //read button clicked
                   if(isset($_POST['read'])){
                       $result = getData();

                       if($result){

                           while ($row = mysqli_fetch_assoc($result)){ ?>

                               <tr>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['contact_id']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['first_name']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['middle_name']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['last_name']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['birth_date']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['anni_date']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['home_phone']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['cell_phone']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['home_address']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['home_city']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['home_state']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['home_zip']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['work_phone']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['work_address']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['work_city']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['work_state']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['work_zip']; ?></td>
                                   <td ><i class="fas fa-edit btnedit" data-id="<?php echo $row['contact_id']; ?>"></i></td>
                               </tr>

                   <?php
                        }

                       }
                   }


                   ?>
                   <?php

                    //search button clicked
                   if(isset($_POST['Esearch'])){
                    
                       $result_search = getSearchData();

                       if($result_search){
                        
                           while ($row = mysqli_fetch_assoc($result_search)){ ?>

                               <tr>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['contact_id']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['first_name']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['middle_name']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['last_name']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['birth_date']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['anni_date']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['home_phone']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['cell_phone']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['home_address']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['home_city']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['home_state']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['home_zip']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['work_phone']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['work_address']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['work_city']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['work_state']; ?></td>
                                   <td data-id="<?php echo $row['contact_id']; ?>"><?php echo $row['work_zip']; ?></td>
                                   <td ><i class="fas fa-edit btnedit" data-id="<?php echo $row['contact_id']; ?>"></i></td>
                               </tr>

                   <?php
                       }
                          
                       }
                   }
                     ?>
                </tbody>
            </table>
            </div>
         </div>     
        </div>
        </main>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script src="../project1/main.js"></script>        
</body>
</html>