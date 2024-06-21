<c:import url="include/header.jsp"></c:import>
	
	
 	<div id="corps">
		<h1 id="titre-principal">Ajout d'un utilisateur</h1>
		<h2><c:out value="${ statusMessage }" /> </h2>
		<form method="post">
		
			<div class="formItem">
				<label>Nom</label>
				<input type="text" name="nom" value= '<c:out value="${ utilisateur.nom}" />'>
				<span><c:out value = ' ${ erreurs.nom }' /></span>
			</div>
			<div class="formItem">
				<label>Prénom</label>
				<input type="text" name="prenom" value= '<c:out value="${ utilisateur.prenom}" />'>
				<span><c:out value = ' ${ erreurs.prenom }' /></span>
			
			</div>
			<div class="formItem">
				<label>Login</label>
				<input type="text" name="login" value= '<c:out value="${ utilisateur.login}" />'>
				<span><c:out value = ' ${ erreurs.login }' /></span>
			
			</div>
			<div class="formItem">
				<label>Password</label>
				<input type="password" name="password">
				<span><c:out value = ' ${ erreurs.password }' /></span>
			</div>
			<div class="formItem">
				<label> Confirm Password</label>
				<input type="password" name="passwordBis">
				<span><c:out value = ' ${ erreurs.passwordBis }' /></span>
			
			</div>
			<div class="formItem">
				<label></label>
				<input type="submit" value="Ajouter">
			</div>
		</form>
	</div>
	<div id="pied">Copyright Master2 ESP &copy; Décembre 2021</div>
	
</body>
</html>