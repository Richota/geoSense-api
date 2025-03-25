/**
 * Interface defining operations for company management.
 */
public interface ICompanyService {
    /**
     * Retrieves all companies in the system.
     *
     * @return List of all companies
     */
    List<CompanyDTO> getAllCompanies();

    /**
     * Creates a new company.
     *
     * @param companyDTO The company data to create
     * @return The created company as DTO
     */
    CompanyDTO createCompany(CompanyDTO companyDTO);
}