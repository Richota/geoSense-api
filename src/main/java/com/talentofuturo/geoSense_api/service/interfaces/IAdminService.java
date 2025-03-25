/**
 * Interface defining administrative operations for company management.
 */
public interface IAdminService {
    /**
     * Creates a new company associated with an admin.
     *
     * @param adminId The ID of the admin creating the company
     * @param company The company details to be created
     * @return The created company entity
     * @throws RuntimeException if admin is not found
     */
    Company createCompany(Long adminId, Company company);

    /**
     * Updates an existing company's information.
     *
     * @param companyId The ID of the company to update
     * @param companyDetails The updated company information
     * @return The updated company entity
     * @throws RuntimeException if company is not found
     */
    Company updateCompany(Long companyId, Company companyDetails);

    /**
     * Deletes a company by its ID.
     *
     * @param companyId The ID of the company to delete
     */
    void deleteCompany(Long companyId);

    /**
     * Retrieves all companies managed by a specific admin.
     *
     * @param adminUsername The username of the admin
     * @return List of companies associated with the admin
     */
    List<Company> getAllCompaniesByAdmin(String adminUsername);
}