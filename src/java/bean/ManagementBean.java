package bean;

import db.Documents;
import db.Homecareplan;
import db.Appointments;
import db.Commissions;
import db.Conferencecalls;
import db.Conferencecallparticipants;
import db.Medicalimages;
import db.Medicalrecords;
import db.Notifications;
import db.Pages;
import db.Patientprofiles;
import db.Paymentdetails;
import db.Payments;
import db.Permissions;
import db.Prescriptions;
import db.Roles;
import db.Sensordata;
import db.Users;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.management.Notification;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named
@RequestScoped
public class ManagementBean {

    // Homecare Plan Fields
    private Homecareplan newHomecarePlan = new Homecareplan();
    private List<Homecareplan> homecarePlans;
    private String statusFilter;

    // Document Fields
    private Documents newDocument = new Documents();
    private List<Documents> documents;
    private String documentTypeFilter;

    // Appointment Fields
    private Appointments newAppointment = new Appointments();
    private List<Appointments> appointments;
    private String appointmentStatusFilter;

    // Commission Fields
    private Commissions newCommission = new Commissions();
    private List<Commissions> commissions;

    // Conference Call Fields
    private Conferencecalls newConferenceCall = new Conferencecalls();
    private List<Conferencecalls> conferenceCalls;

    // Conference Call Participant Fields
    private Conferencecallparticipants newConferenceCallParticipant = new Conferencecallparticipants();
    private List<Conferencecallparticipants> conferenceCallParticipants;

    //medical images
    private Medicalimages newMedicalImages = new Medicalimages();
    private List<Medicalimages> medicalImages;

    //medical records 
    private Medicalrecords newMedicalrecords = new Medicalrecords();
    private List<Medicalrecords> Medicalrecords;

    //notifications 
    private Notifications newNotifications = new Notifications();
    private List<Notifications> Notifications;

    //patient profile 
    //notifications 
    private Patientprofiles newPatientProfile = new Patientprofiles();
    private List<Patientprofiles> PatientProfiles;

    //payment details 
    private Paymentdetails newPaymentDetail = new Paymentdetails();
    private List<Paymentdetails> PaymentDetails;

    //payment details 
    private Payments newPayment = new Payments();
    private List<Payments> Payments;

    //permissions 
    private Permissions newPermission = new Permissions();
    private List<Permissions> Permissions;

    //prescriptions
    private Prescriptions prescriptionInstance = new Prescriptions();
    private List<Prescriptions> Prescriptions;

    //pages 
    //notifications 
    private Pages newPage = new Pages();
    private List<Pages> Pages;
    //new user
    //medical images
    private Users newUser = new Users();
    private List<Users> Users;

    //roles
    private Roles roleInstance = new Roles();
    private List<Roles> Roles;

    //sensor data 
    private Sensordata sensorDataInstance = new Sensordata();
    private List<Sensordata> Sensordata;

    @PersistenceContext(unitName = "homeCarePU")
    private EntityManager em;
    
    
    //login
        public Users login() {
        try {
            // Check if the user exists with the provided username and password
            TypedQuery<Users> query = em.createNamedQuery("Users.findByUsername", Users.class);
            query.setParameter("username", user.getUsername());
            
            Users user = query.getSingleResult();
            
            // Check if the password matches (you should use a hashed password comparison in a real-world scenario)
            if (user.getPassword().equals(user.getPassword())) {
                // Update last login timestamp
                user.setLastLogin(new Date());
                em.getTransaction().begin();
                em.merge(user);  // Save the last login time
                em.getTransaction().commit();
                
                // Optionally, you can check for other conditions like account status (active/inactive)
                if (!user.getIsActive()) {
                    throw new RuntimeException("Your account is inactive. Please contact support.");
                }
                
                
            // Fetch the roles of the user (roles are linked via the `rolesCollection` field in `Users`)
            List<Roles> roles = em.createQuery("SELECT r FROM Roles r JOIN r.usersCollection u WHERE u.username = :username", Roles.class)
                                  .setParameter("username", user.getUsername())
                                  .getResultList();
            
            // Add roles to the user object (assuming the `Users` object has a setter for roles)
            user.setRoleId(roles.get(0));
                
                return user;
            } else {
                throw new RuntimeException("Invalid credentials.");
            }
        } catch (NoResultException e) {
            throw new RuntimeException("User not found.");
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

    public boolean isAccountLocked(String username) {
        try {
            TypedQuery<Users> query = em.createNamedQuery("Users.findByUsername", Users.class);
            query.setParameter("username", username);
            Users user = query.getSingleResult();
            
            // Example logic to check account status (locked after multiple failed login attempts)
            // Adjust this logic according to your system's requirements (e.g., track login attempts)
            // For now, let's assume the account is locked if the user has no successful login timestamp.
            return user.getLastLogin() == null;
        } catch (NoResultException e) {
            throw new RuntimeException("User not found.");
        }
    }

    // CRUD for Homecare Plan
    public void createHomecarePlan() {
        getNewHomecarePlan().setCreatedAt(new Date());
        getEm().persist(getNewHomecarePlan());
        setNewHomecarePlan(new Homecareplan()); // Reset
        setHomecarePlans(null); // Refresh list
    }

    public List<Homecareplan> getHomecarePlans() {
        return getEm().createNamedQuery("Homecareplan.findAll", Homecareplan.class).getResultList();
    }

    public void deleteHomecarePlan() {
        Homecareplan plan = getEm().find(Homecareplan.class, getNewHomecarePlan().getPlanId());
        if (plan != null) {
            getEm().remove(plan);
        }
        setHomecarePlans(null); // Refresh list
    }

    // CRUD for Documents
    public void createDocument() {
        getNewDocument().setUploadedAt(new Date());
        getEm().persist(getNewDocument());
        setNewDocument(new Documents()); // Reset
        setDocuments(null); // Refresh list
    }

    public List<Documents> getDocuments() {
        return getEm().createNamedQuery("Documents.findAll", Documents.class).getResultList();

    }

    public void deleteDocument(Integer documentId) {
        Documents document = getEm().find(Documents.class, documentId);
        if (document != null) {
            getEm().remove(document);
        }
        setDocuments(null); // Refresh list
    }

    // CRUD for Appointments
    public void createAppointment() {
        getNewAppointment().setCreatedAt(new Date());
        getEm().persist(getNewAppointment());
        setNewAppointment(new Appointments()); // Reset after saving
        setAppointments(null); // Refresh list
    }

    public List<Appointments> getAppointments() {
        return getEm().createNamedQuery("Appointments.findAll", Appointments.class).getResultList();

    }

    public void deleteAppointment() {
        Appointments appointment = getEm().find(Appointments.class, getNewAppointment().getAppointmentId());
        if (appointment != null) {
            getEm().remove(appointment);
        }
        setAppointments(null); // Refresh list
    }

    // CRUD for Commissions
    public void createCommission() {
        getEm().persist(getNewAppointment());
        setNewCommission(new Commissions()); // Reset
        setCommissions(null); // Refresh list
    }

    public List<Commissions> getCommissions() {
        return getEm().createNamedQuery("Commissions.findAll", Commissions.class).getResultList();

    }

    public void deleteCommission(Integer commissionId) {
        Commissions commission = getEm().find(Commissions.class, commissionId);
        if (commission != null) {
            getEm().remove(commission);
        }
        setCommissions(null); // Refresh list
    }

    // CRUD for Conference Calls
    public void createConferenceCall() {
        getNewConferenceCall().setCreatedAt(new Date());
        getEm().persist(getNewConferenceCall());
        setNewConferenceCall(new Conferencecalls()); // Reset
        setConferenceCalls(null); // Refresh list
    }

    public List<Conferencecalls> getConferenceCalls() {
        return getEm().createNamedQuery("Conferencecalls.findAll", Conferencecalls.class).getResultList();
    }

    public void deleteConferenceCall() {
        Conferencecalls call = getEm().find(Conferencecalls.class, getNewConferenceCall().getCallId());
        if (call != null) {
            getEm().remove(call);
        }
    }

    public void updateConferenceCall() {
        Conferencecalls existingCall = getEm().find(Conferencecalls.class, getNewConferenceCall().getCallId());
        if (existingCall != null) {
            existingCall.setScheduledAt(new java.util.Date());
            existingCall.setUpdatedAt(new Date());
            getEm().merge(existingCall); // Save the changes
        }
    }

    // CRUD for Conference Call Participants
    public void addConferenceCallParticipant() {
        getNewConferenceCallParticipant().setJoinedAt(new java.util.Date());
        getEm().persist(getNewConferenceCallParticipant());
        setNewConferenceCallParticipant(new Conferencecallparticipants()); // Reset
        setConferenceCallParticipants(null); // Refresh list
    }

    public List<Conferencecallparticipants> getConferenceCallParticipants() {
        return getEm().createNamedQuery("Conferencecallparticipants.findAll", Conferencecallparticipants.class).getResultList();
    }

    public void removeConferenceCallParticipant() {
        Conferencecallparticipants participant = getEm().find(Conferencecallparticipants.class, getNewConferenceCallParticipant().getParticipantId());
        if (participant != null) {
            getEm().remove(participant);
        }
    }

    public void updateConferenceCallParticipant() {
        Conferencecallparticipants existingParticipant = getEm().find(Conferencecallparticipants.class, getNewConferenceCallParticipant().getParticipantId());
        if (existingParticipant != null) {
            getEm().merge(existingParticipant); // Save the changes
        }
    }

    //medical images 
    // Create a new medical image
    public Medicalimages createMedicalImage() {
        Medicalimages image = new Medicalimages();
        image.setImageType(getNewMedicalImages().getImageType());
        image.setImageUrl(getNewMedicalImages().getImageUrl());
        image.setImageDescription(getNewMedicalImages().getImageDescription());
        image.setUploadedAt(new Date());
        image.setPatientId(getNewMedicalImages().getPatientId());
        image.setUploadedBy(getNewUser());
        getEm().persist(image);
        return image;
    }

    // Find a medical image by ID
    public Medicalimages findMedicalImageById(Integer imageId) {
        return getEm().find(Medicalimages.class, imageId);
    }

    // Update a medical image
    public Medicalimages updateMedicalImage() {
        Medicalimages image = findMedicalImageById(getNewMedicalImages().getImageId());
        if (image != null) {
            if (getNewMedicalImages().getImageDescription() != null) {
                image.setImageDescription(getNewMedicalImages().getImageDescription());
            }
            if (getNewMedicalImages().getImageUrl() != null) {
                image.setImageUrl(getNewMedicalImages().getImageUrl());
            }
            getEm().merge(image);
        }
        return image;
    }

    // Delete a medical image by ID
    public boolean deleteMedicalImage(Integer imageId) {
        Medicalimages image = findMedicalImageById(imageId);
        if (image != null) {
            getEm().remove(image);
            return true;
        }
        return false;
    }

    // Find all medical images
    public List<Medicalimages> findAllMedicalImages() {
        return getEm().createNamedQuery("Medicalimages.findAll", Medicalimages.class).getResultList();
    }

    //medical records 
    // Create a new medical record
    public Medicalrecords createMedicalRecord() {
        Medicalrecords medicalRecord = new Medicalrecords();
        medicalRecord.setDiagnosis(getNewMedicalrecords().getDiagnosis());
        medicalRecord.setPrescriptions(getNewMedicalrecords().getPrescriptions());
        medicalRecord.setReferrals(getNewMedicalrecords().getReferrals());
        medicalRecord.setHealthPlan(getNewMedicalrecords().getHealthPlan());
        medicalRecord.setShortTermGoals(getNewMedicalrecords().getShortTermGoals());
        medicalRecord.setLongTermGoals(getNewMedicalrecords().getLongTermGoals());
        medicalRecord.setVisitDate(getNewMedicalrecords().getVisitDate());
        medicalRecord.setRecordStatus(getNewMedicalrecords().getRecordStatus());
        medicalRecord.setPatientId(getNewMedicalrecords().getPatientId());
        medicalRecord.setDoctorId(getNewMedicalrecords().getDoctorId());
        medicalRecord.setCreatedAt(new Date());
        getEm().persist(medicalRecord);
        return medicalRecord;
    }

    // Find a medical record by ID
    public Medicalrecords findMedicalRecordById(Integer recordId) {
        return getEm().find(Medicalrecords.class, recordId);
    }

    // Update the status of a medical record
    public Medicalrecords updateMedicalRecordStatus() {
        Medicalrecords medicalRecord = findMedicalRecordById(getNewMedicalrecords().getRecordId());
        if (medicalRecord != null) {
            medicalRecord.setRecordStatus(getNewMedicalrecords().getRecordStatus());
            medicalRecord.setUpdatedAt(new Date());
            getEm().merge(medicalRecord);
        }
        return medicalRecord;
    }

    // Delete a medical record by ID
    public boolean deleteMedicalRecord() {
        Medicalrecords medicalRecord = findMedicalRecordById(getNewMedicalrecords().getRecordId());
        if (medicalRecord != null) {
            getEm().remove(medicalRecord);
            return true;
        }
        return false;
    }

    // Find all medical records
    public List<Medicalrecords> findAllMedicalRecords() {
        return getEm().createNamedQuery("Medicalrecords.findAll", Medicalrecords.class).getResultList();
    }

    //notifications 
    // Create a new notification
    public Notifications createNotification() {
        Notifications notification = new Notifications();
        notification.setMessage(getNewNotifications().getMessage());
        notification.setStatus(getNewNotifications().getStatus());
        notification.setUserId(getNewNotifications().getUserId());
        notification.setCreatedAt(new Date());
        getEm().persist(notification);
        return notification;
    }

    // Find a notification by ID
    public Notifications findNotificationById(Integer notificationId) {
        return getEm().find(Notifications.class, notificationId);
    }

    // Update the status of a notification
    public Notifications updateNotificationStatus() {
        Notifications notification = findNotificationById(getNewNotifications().getNotificationId());
        if (notification != null) {
            notification.setStatus(getNewNotifications().getStatus());
            notification.setSentAt(new Date());
            getEm().merge(notification);
        }
        return notification;
    }

    // Delete a notification by ID
    public boolean deleteNotification() {
        Notifications notification = findNotificationById(getNewNotifications().getNotificationId());
        if (notification != null) {
            getEm().remove(notification);
            return true;
        }
        return false;
    }

    // Find all notifications
    public List<Notifications> findAllNotifications() {
        return getEm().createNamedQuery("Notifications.findAll", Notifications.class).getResultList();
    }

    //pages 
    // CRUD for Pages
    public void createPage() {
        getNewPage().setPageName(getNewPage().getPageName());
        getEm().persist(getNewPage());
        setNewPage(new Pages()); // Reset

    }

    public List<Pages> getPages() {
        return getEm().createNamedQuery("Pages.findAll", Pages.class).getResultList();
    }

    public void deletePage() {
        Pages page = getEm().find(Pages.class, getNewPage().getPageId());
        if (page != null) {
            getEm().remove(page);
        }
    }

// New Update Function
    public void updatePage(Pages updatedPage) {
        Pages existingPage = getEm().find(Pages.class, updatedPage.getPageId());
        if (existingPage != null) {
            existingPage.setPageName(updatedPage.getPageName());
            getEm().merge(existingPage); // Save changes
        }
    }

    //patient profile 
    public void createPatientProfile() {
        getNewPatientProfile().setCreatedAt(new Date());
        getNewPatientProfile().setUpdatedAt(new Date());
        getEm().persist(getNewPatientProfile());
        setNewPatientProfile(new Patientprofiles()); // Reset

    }

    public List<Patientprofiles> getPatientProfiles() {
        return getEm().createNamedQuery("Patientprofiles.findAll", Patientprofiles.class).getResultList();
    }

    public void deletePatientProfile() {
        Patientprofiles profile = getEm().find(Patientprofiles.class, getNewPatientProfile().getProfileId());
        if (profile != null) {
            getEm().remove(profile);
        }
    }

// New Update Function
    public void updatePatientProfile(Patientprofiles updatedProfile) {
        Patientprofiles existingProfile = getEm().find(Patientprofiles.class, updatedProfile.getProfileId());
        if (existingProfile != null) {
            existingProfile.setEmergencyContact(updatedProfile.getEmergencyContact());
            existingProfile.setInsuranceNumber(updatedProfile.getInsuranceNumber());
            existingProfile.setMedicalHistory(updatedProfile.getMedicalHistory());
            existingProfile.setHealthConditions(updatedProfile.getHealthConditions());
            existingProfile.setUpdatedAt(new Date());
            existingProfile.setUserId(updatedProfile.getUserId());
            existingProfile.setPrimaryDoctorId(updatedProfile.getPrimaryDoctorId());
            getEm().merge(existingProfile); // Save changes
        }
    }

    //payment details
    public void createPaymentDetail() {
        getEm().persist(getNewPaymentDetail());
        setNewPaymentDetail(new Paymentdetails()); // Reset
    }

    public List<Paymentdetails> getPaymentDetails() {
        return getEm().createNamedQuery("Paymentdetails.findAll", Paymentdetails.class).getResultList();
    }

    public void deletePaymentDetail() {
        Paymentdetails detail = getEm().find(Paymentdetails.class, getNewPaymentDetail().getPaymentDetailId());
        if (detail != null) {
            getEm().remove(detail);
        }
    }

    // Update Function
    public void updatePaymentDetail(Paymentdetails updatedDetail) {
        Paymentdetails existingDetail = getEm().find(Paymentdetails.class, updatedDetail.getPaymentDetailId());
        if (existingDetail != null) {
            existingDetail.setServiceType(updatedDetail.getServiceType());
            existingDetail.setDescription(updatedDetail.getDescription());
            existingDetail.setServiceId(updatedDetail.getServiceId());
            existingDetail.setPaymentId(updatedDetail.getPaymentId());
            getEm().merge(existingDetail); // Save changes
        }
    }

    //payments 
    public void createPayment() {
        try {
            getEm().persist(getNewPayment());
            setNewPayment(new Payments()); // Reset
        } catch (Exception e) {
            throw new RuntimeException("Error creating payment: " + e.getMessage(), e);
        }
    }

    public List<Payments> findAllPayments() {
        return getEm().createNamedQuery("Payments.findAll", Payments.class).getResultList();
    }

    public void updatePayment() {
        Payments existingPayment = findPaymentById(getNewPayment().getPaymentId());

        if (existingPayment != null) {
            existingPayment.setAmount(getNewPayment().getAmount());
            existingPayment.setPaymentDate(getNewPayment().getPaymentDate());
            existingPayment.setPaymentStatus(getNewPayment().getPaymentStatus());
            existingPayment.setPaymentMethod(getNewPayment().getPaymentMethod());
            existingPayment.setCreatedAt(getNewPayment().getCreatedAt());
            existingPayment.setUpdatedAt(getNewPayment().getUpdatedAt());
            existingPayment.setCommissionPercentage(getNewPayment().getCommissionPercentage());
            existingPayment.setCommissionStatus(getNewPayment().getCommissionStatus());
            existingPayment.setUserId(getNewPayment().getUserId());
            existingPayment.setCreatedBy(getNewPayment().getCreatedBy());

            getEm().merge(existingPayment);
        } else {
            throw new IllegalArgumentException("Payment not found for update: " + existingPayment.getPaymentId());
        }
    }

    public void deletePayment() {
        Payments payment = findPaymentById(getNewPayment().getPaymentId());
        if (payment != null) {
            getEm().remove(payment);
        } else {
            throw new IllegalArgumentException("Payment not found for deletion: " + getNewPayment().getPaymentId());
        }
    }

    public Payments findPaymentById(Integer id) {
        return getEm().find(Payments.class, id);
    }

    //permissions
    public void createPermission() {
        try {
            getEm().persist(getNewPermission());
        } catch (Exception e) {
            throw new RuntimeException("Error creating permission: " + e.getMessage(), e);
        }
    }

    public void updatePermission() {
        Permissions existingPermission = findPermissionById(getNewPermission().getPermissionId());

        if (existingPermission != null) {
            existingPermission.setCanCreate(getNewPermission().getCanCreate());
            existingPermission.setCanRead(getNewPermission().getCanRead());
            existingPermission.setCanUpdate(getNewPermission().getCanUpdate());
            existingPermission.setCanDelete(getNewPermission().getCanDelete());
            existingPermission.setPageId(getNewPermission().getPageId());
            existingPermission.setRoleId(getNewPermission().getRoleId());

            getEm().merge(existingPermission);
        } else {
            throw new IllegalArgumentException("Permission not found for update: " + getNewPermission().getPermissionId());
        }
    }

    public Permissions findPermissionById(Integer id) {
        return getEm().find(Permissions.class, id);
    }

    public List<Permissions> findAllPermissions() {
        return getEm().createNamedQuery("Permissions.findAll", Permissions.class).getResultList();
    }

    //prescriptions
    public void createPrescription() {
        if (getPrescriptionInstance() == null) {
            throw new IllegalArgumentException("No prescription instance set.");
        }

        try {
            getEm().persist(getPrescriptionInstance());
        } catch (Exception e) {
            throw new RuntimeException("Error creating prescription: " + e.getMessage(), e);
        }
    }

    // Read all prescriptions
    public List<Prescriptions> findAllPrescriptions() {
        return getEm().createNamedQuery("Prescriptions.findAll", Prescriptions.class).getResultList();
    }

    // Read prescription by ID (based on the instance's ID)
    public Prescriptions findPrescriptionById() {
        if (getPrescriptionInstance() == null || getPrescriptionInstance().getPrescriptionId() == null) {
            throw new IllegalArgumentException("Prescription ID not set in the instance.");
        }

        return getEm().find(Prescriptions.class, getPrescriptionInstance().getPrescriptionId());
    }

    // Update an existing prescription
    public void updatePrescription() {
        if (getPrescriptionInstance() == null || getPrescriptionInstance().getPrescriptionId() == null) {
            throw new IllegalArgumentException("No prescription instance or ID set.");
        }

        try {
            getEm().merge(getPrescriptionInstance());
        } catch (Exception e) {
            throw new RuntimeException("Error updating prescription: " + e.getMessage(), e);
        }
    }

    // Delete a prescription (based on the instance's ID)
    public void deletePrescription() {
        if (getPrescriptionInstance() == null || getPrescriptionInstance().getPrescriptionId() == null) {
            throw new IllegalArgumentException("No prescription instance or ID set.");
        }

        Prescriptions prescriptionToDelete = getEm().find(Prescriptions.class, getPrescriptionInstance().getPrescriptionId());
        if (prescriptionToDelete != null) {
            getEm().remove(prescriptionToDelete);
        } else {
            throw new IllegalArgumentException("Prescription not found for deletion.");
        }
    }

    //roles
    // Create a new role
    public void createRole() {
        if (getRoleInstance() == null) {
            throw new IllegalArgumentException("No role instance set.");
        }

        try {
            getEm().persist(getRoleInstance());
        } catch (Exception e) {
            throw new RuntimeException("Error creating role: " + e.getMessage(), e);
        }
    }

    // Read all roles
    public List<Roles> findAllRoles() {
        return getEm().createNamedQuery("Roles.findAll", Roles.class).getResultList();
    }

    // Read role by ID (based on the instance's ID)
    public Roles findRoleById() {
        if (getRoleInstance() == null || getRoleInstance().getRoleId() == null) {
            throw new IllegalArgumentException("Role ID not set in the instance.");
        }

        return getEm().find(Roles.class, getRoleInstance().getRoleId());
    }

    // Update an existing role
    public void updateRole() {
        if (getRoleInstance() == null || getRoleInstance().getRoleId() == null) {
            throw new IllegalArgumentException("No role instance or ID set.");
        }

        try {
            getEm().merge(getRoleInstance());
        } catch (Exception e) {
            throw new RuntimeException("Error updating role: " + e.getMessage(), e);
        }
    }

    // Delete a role (based on the instance's ID)
    public void deleteRole() {
        if (getRoleInstance() == null || getRoleInstance().getRoleId() == null) {
            throw new IllegalArgumentException("No role instance or ID set.");
        }

        Roles roleToDelete = getEm().find(Roles.class, getRoleInstance().getRoleId());
        if (roleToDelete != null) {
            getEm().remove(roleToDelete);
        } else {
            throw new IllegalArgumentException("Role not found for deletion.");
        }
    }

    //sensor data 
    // Create a new sensor data record
    public void createSensorData() {
        if (getSensorDataInstance() == null) {
            throw new IllegalArgumentException("No sensor data instance set.");
        }

        try {
            getEm().persist(getSensorDataInstance());
        } catch (Exception e) {
            throw new RuntimeException("Error creating sensor data: " + e.getMessage(), e);
        }
    }

    // Read all sensor data records
    public List<Sensordata> findAllSensorData() {
        return getEm().createNamedQuery("Sensordata.findAll", Sensordata.class).getResultList();
    }

    // Read sensor data by ID (based on the instance's ID)
    public Sensordata findSensorDataById() {
        if (getSensorDataInstance() == null || getSensorDataInstance().getSensorDataId() == null) {
            throw new IllegalArgumentException("Sensor Data ID not set in the instance.");
        }

        return getEm().find(Sensordata.class, getSensorDataInstance().getSensorDataId());
    }

    // Update an existing sensor data record
    public void updateSensorData() {
        if (getSensorDataInstance() == null || getSensorDataInstance().getSensorDataId() == null) {
            throw new IllegalArgumentException("No sensor data instance or ID set.");
        }

        try {
            getEm().merge(getSensorDataInstance());
        } catch (Exception e) {
            throw new RuntimeException("Error updating sensor data: " + e.getMessage(), e);
        }
    }

    // Delete a sensor data record (based on the instance's ID)
    public void deleteSensorData() {
        if (getSensorDataInstance() == null || getSensorDataInstance().getSensorDataId() == null) {
            throw new IllegalArgumentException("No sensor data instance or ID set.");
        }

        Sensordata dataToDelete = getEm().find(Sensordata.class, getSensorDataInstance().getSensorDataId());
        if (dataToDelete != null) {
            getEm().remove(dataToDelete);
        } else {
            throw new IllegalArgumentException("Sensor data not found for deletion.");
        }
    }

    //users 
    private Users user = new Users();
    private Integer userId;

    // CREATE
    public String createUser() {
        try {
            getEm().persist(getUser());
            return "userList";  // Navigate to a user list page
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Handle error, navigate to error page
        }
    }

    // READ (Single User)
    public Users getUserById() {
        if (getUserId() != null) {
            return getEm().find(Users.class, getUserId());
        }
        return null;
    }

    // READ (All Users)
    public List<Users> getAllUsers() {
        return getEm().createNamedQuery("Users.findAll", Users.class).getResultList();
    }

    // UPDATE
    public String updateUser() {
        try {
            Users existingUser = getEm().find(Users.class, getUser().getUserId());
            if (existingUser != null) {
                existingUser.setUsername(getUser().getUsername());
                existingUser.setPassword(getUser().getPassword());
                existingUser.setFullName(getUser().getFullName());
                existingUser.setEmail(getUser().getEmail());
                existingUser.setPhone(getUser().getPhone());
                existingUser.setDateOfBirth(getUser().getDateOfBirth());
                existingUser.setSex(getUser().getSex());
                existingUser.setAddress(getUser().getAddress());
                existingUser.setIsActive(getUser().getIsActive());
                existingUser.setLastLogin(getUser().getLastLogin());
                existingUser.setUpdatedAt(getUser().getUpdatedAt());
                getEm().merge(existingUser);
                return "userList";  // Navigate to user list page
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";  // Handle error, navigate to error page
    }

    // DELETE
    public String deleteUser() {
        try {
            Users existingUser = getEm().find(Users.class, getUser().getUserId());
            if (existingUser != null) {
                getEm().remove(existingUser);
                return "userList";  // Navigate to user list page
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error"; // Handle error, navigate to error page
    }

    // Getters and Setters
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Getters and Setters
    public Homecareplan getNewHomecarePlan() {
        return newHomecarePlan;
    }

    public void setNewHomecarePlan(Homecareplan newHomecarePlan) {
        this.newHomecarePlan = newHomecarePlan;
    }

    public String getStatusFilter() {
        return statusFilter;
    }

    public void setStatusFilter(String statusFilter) {
        this.statusFilter = statusFilter;
    }

    // For Documents
    public Documents getNewDocument() {
        return newDocument;
    }

    public void setNewDocument(Documents newDocument) {
        this.newDocument = newDocument;
    }

    public String getDocumentTypeFilter() {
        return documentTypeFilter;
    }

    public void setDocumentTypeFilter(String documentTypeFilter) {
        this.documentTypeFilter = documentTypeFilter;
    }

    // For Appointments
    public Appointments getNewAppointment() {
        return newAppointment;
    }

    public void setNewAppointment(Appointments newAppointment) {
        this.newAppointment = newAppointment;
    }

    public String getAppointmentStatusFilter() {
        return appointmentStatusFilter;
    }

    public void setAppointmentStatusFilter(String appointmentStatusFilter) {
        this.appointmentStatusFilter = appointmentStatusFilter;
    }

    // For Commissions
    public Commissions getNewCommission() {
        return newCommission;
    }

    public void setNewCommission(Commissions newCommission) {
        this.newCommission = newCommission;
    }

    // For Conference Calls
    public Conferencecalls getNewConferenceCall() {
        return newConferenceCall;
    }

    public void setNewConferenceCall(Conferencecalls newConferenceCall) {
        this.newConferenceCall = newConferenceCall;
    }

    // For Conference Call Participants
    public Conferencecallparticipants getNewConferenceCallParticipant() {
        return newConferenceCallParticipant;
    }

    public void setNewConferenceCallParticipant(Conferencecallparticipants newConferenceCallParticipant) {
        this.newConferenceCallParticipant = newConferenceCallParticipant;
    }

    /**
     * @param conferenceCallParticipants the conferenceCallParticipants to set
     */
    public void setConferenceCallParticipants(List<Conferencecallparticipants> conferenceCallParticipants) {
        this.conferenceCallParticipants = conferenceCallParticipants;
    }

    /**
     * @return the newMedicalImages
     */
    public Medicalimages getNewMedicalImages() {
        return newMedicalImages;
    }

    /**
     * @param newMedicalImages the newMedicalImages to set
     */
    public void setNewMedicalImages(Medicalimages newMedicalImages) {
        this.newMedicalImages = newMedicalImages;
    }

    /**
     * @return the medicalImages
     */
    public List<Medicalimages> getMedicalImages() {
        return medicalImages;
    }

    /**
     * @param medicalImages the medicalImages to set
     */
    public void setMedicalImages(List<Medicalimages> medicalImages) {
        this.medicalImages = medicalImages;
    }

    /**
     * @return the newUser
     */
    public Users getNewUser() {
        return newUser;
    }

    /**
     * @param newUser the newUser to set
     */
    public void setNewUser(Users newUser) {
        this.newUser = newUser;
    }

    /**
     * @return the Users
     */
    public List<Users> getUsers() {
        return Users;
    }

    /**
     * @param Users the Users to set
     */
    public void setUsers(List<Users> Users) {
        this.Users = Users;
    }

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the newMedicalrecords
     */
    public Medicalrecords getNewMedicalrecords() {
        return newMedicalrecords;
    }

    /**
     * @param newMedicalrecords the newMedicalrecords to set
     */
    public void setNewMedicalrecords(Medicalrecords newMedicalrecords) {
        this.newMedicalrecords = newMedicalrecords;
    }

    /**
     * @return the Medicalrecords
     */
    public List<Medicalrecords> getMedicalrecords() {
        return Medicalrecords;
    }

    /**
     * @param Medicalrecords the Medicalrecords to set
     */
    public void setMedicalrecords(List<Medicalrecords> Medicalrecords) {
        this.Medicalrecords = Medicalrecords;
    }

    /**
     * @param homecarePlans the homecarePlans to set
     */
    public void setHomecarePlans(List<Homecareplan> homecarePlans) {
        this.homecarePlans = homecarePlans;
    }

    /**
     * @param documents the documents to set
     */
    public void setDocuments(List<Documents> documents) {
        this.documents = documents;
    }

    /**
     * @param appointments the appointments to set
     */
    public void setAppointments(List<Appointments> appointments) {
        this.appointments = appointments;
    }

    /**
     * @param commissions the commissions to set
     */
    public void setCommissions(List<Commissions> commissions) {
        this.commissions = commissions;
    }

    /**
     * @param conferenceCalls the conferenceCalls to set
     */
    public void setConferenceCalls(List<Conferencecalls> conferenceCalls) {
        this.conferenceCalls = conferenceCalls;
    }

    /**
     * @return the newNotifications
     */
    public Notifications getNewNotifications() {
        return newNotifications;
    }

    /**
     * @param newNotifications the newNotifications to set
     */
    public void setNewNotifications(Notifications newNotifications) {
        this.newNotifications = newNotifications;
    }

    /**
     * @return the Notifications
     */
    public List<Notifications> getNotifications() {
        return Notifications;
    }

    /**
     * @param Notifications the Notifications to set
     */
    public void setNotifications(List<Notifications> Notifications) {
        this.Notifications = Notifications;
    }

    /**
     * @return the newPatientProfile
     */
    public Patientprofiles getNewPatientProfile() {
        return newPatientProfile;
    }

    /**
     * @param newPatientProfile the newPatientProfile to set
     */
    public void setNewPatientProfile(Patientprofiles newPatientProfile) {
        this.newPatientProfile = newPatientProfile;
    }

    /**
     * @param PatientProfiles the PatientProfiles to set
     */
    public void setPatientProfiles(List<Patientprofiles> PatientProfiles) {
        this.PatientProfiles = PatientProfiles;
    }

    /**
     * @return the newPaymentDetail
     */
    public Paymentdetails getNewPaymentDetail() {
        return newPaymentDetail;
    }

    /**
     * @param newPaymentDetail the newPaymentDetail to set
     */
    public void setNewPaymentDetail(Paymentdetails newPaymentDetail) {
        this.newPaymentDetail = newPaymentDetail;
    }

    /**
     * @param PaymentDetails the PaymentDetails to set
     */
    public void setPaymentDetails(List<Paymentdetails> PaymentDetails) {
        this.PaymentDetails = PaymentDetails;
    }

    /**
     * @return the newPayment
     */
    public Payments getNewPayment() {
        return newPayment;
    }

    /**
     * @param newPayment the newPayment to set
     */
    public void setNewPayment(Payments newPayment) {
        this.newPayment = newPayment;
    }

    /**
     * @return the Payments
     */
    public List<Payments> getPayments() {
        return Payments;
    }

    /**
     * @param Payments the Payments to set
     */
    public void setPayments(List<Payments> Payments) {
        this.Payments = Payments;
    }

    /**
     * @return the newPermission
     */
    public Permissions getNewPermission() {
        return newPermission;
    }

    /**
     * @param newPermission the newPermission to set
     */
    public void setNewPermission(Permissions newPermission) {
        this.newPermission = newPermission;
    }

    /**
     * @return the Permissions
     */
    public List<Permissions> getPermissions() {
        return Permissions;
    }

    /**
     * @param Permissions the Permissions to set
     */
    public void setPermissions(List<Permissions> Permissions) {
        this.Permissions = Permissions;
    }

    /**
     * @return the prescriptionInstance
     */
    public Prescriptions getPrescriptionInstance() {
        return prescriptionInstance;
    }

    /**
     * @param prescriptionInstance the prescriptionInstance to set
     */
    public void setPrescriptionInstance(Prescriptions prescriptionInstance) {
        this.prescriptionInstance = prescriptionInstance;
    }

    /**
     * @return the Prescriptions
     */
    public List<Prescriptions> getPrescriptions() {
        return Prescriptions;
    }

    /**
     * @param Prescriptions the Prescriptions to set
     */
    public void setPrescriptions(List<Prescriptions> Prescriptions) {
        this.Prescriptions = Prescriptions;
    }

    /**
     * @return the newPage
     */
    public Pages getNewPage() {
        return newPage;
    }

    /**
     * @param newPage the newPage to set
     */
    public void setNewPage(Pages newPage) {
        this.newPage = newPage;
    }

    /**
     * @param Pages the Pages to set
     */
    public void setPages(List<Pages> Pages) {
        this.Pages = Pages;
    }

    /**
     * @return the roleInstance
     */
    public Roles getRoleInstance() {
        return roleInstance;
    }

    /**
     * @param roleInstance the roleInstance to set
     */
    public void setRoleInstance(Roles roleInstance) {
        this.roleInstance = roleInstance;
    }

    /**
     * @return the Roles
     */
    public List<Roles> getRoles() {
        return Roles;
    }

    /**
     * @param Roles the Roles to set
     */
    public void setRoles(List<Roles> Roles) {
        this.Roles = Roles;
    }

    /**
     * @return the sensorDataInstance
     */
    public Sensordata getSensorDataInstance() {
        return sensorDataInstance;
    }

    /**
     * @param sensorDataInstance the sensorDataInstance to set
     */
    public void setSensorDataInstance(Sensordata sensorDataInstance) {
        this.sensorDataInstance = sensorDataInstance;
    }

    /**
     * @return the Sensordata
     */
    public List<Sensordata> getSensordata() {
        return Sensordata;
    }

    /**
     * @param Sensordata the Sensordata to set
     */
    public void setSensordata(List<Sensordata> Sensordata) {
        this.Sensordata = Sensordata;
    }
}
