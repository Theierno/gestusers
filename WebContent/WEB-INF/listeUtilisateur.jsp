
<c:import url="include/header.jsp"></c:import>

	<div id="corps">
		<h1 id="titre-principal">Liste des utilisateurs</h1>
		<c:choose >
			<c:when test="${empty requestScope.utilisateurs}">
				<p>La liste des utilisateurs est pour le moment vide !</p>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<th>ID</th>
						<th>Prénom</th>
						<th>Nom</th>
						<th>Login</th>
						<th>Password</th>
						<th colspan="2">Actions</th>
					</tr>
					<c:forEach var="utilisateur" items="${requestScope.utilisateurs }">
						<tr>
							<td><c:out value="${utilisateur.id}"/> </td>
							<td><c:out value="${utilisateur.prenom}"/></td>
							<td><c:out value="${utilisateur.nom}"/></td>
							<td><c:out value="${utilisateur.login}"/></td>
							<td><c:out value="${utilisateur.password}"/></td>
							<td class="action"><a  href='<c:url value = "/update?id=${utilisateur.id}" />'  >Modifier</a></td>
							<td><a href='<c:url value = "/delete?id=${utilisateur.id}" />' onclick="return confirm('Etes vous sure ?')">Supprimer</a></td>
						</tr>
					</c:forEach>
			
				</table>
			</c:otherwise>
		</c:choose >
	</div>

<c:import url="include/footer.jsp"></c:import>
