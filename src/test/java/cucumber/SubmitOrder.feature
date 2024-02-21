
 @tag
 Feature: Purchase the order from Ecommerce Website
   I want to use this template for my feature file
   
   Backgroung: 
   Given: I landed on Ecommerce Page
   
   @tag2
   
   Scenario Outline: Positive Test of Submitting the order
   
   Given Logged in with username <name> and <password>
   When add product <productName> to cart
   And Checkout <productName> and submit the order
   Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage
   
   Examples:
      |name                     | password   | productName |
      |ratan.glbian@gmail.com   | Ratan@123  | ZARA COAT 3 |