<c:import url="include/header.jsp"></c:import>

	<div id="corps">
		<h1 id="titre-principal">Page d'authentification </h1>
		<div class="${ statusMessage!=null?'messageBox':'none' } ${status?'succes':'erreur' }">
			<h2><c:out value="${ statusMessage }" /></h2>
		</div>
		<form method="post">
			
			<div class="formItem">
				<label>Login</label>
				<input type="text" name="login" value="<c:out value='${utilisateur.login}' />">
			</div>
			<div class="formItem">
				<label>Password</label>
				<input type="password" name="password" value="">
			</div>
			
			<div class="formItem">
				<label></label>
				<input type="submit" value="se connecter">
			</div>
		</form>
	</div>
	<div id="pied">Copyright Master2 ESP &copy; Décembre 2021</div>
</body>
</html>