//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import project.myparking.domain.PasswordEncoder;
//
//@Service
//@Transactional
//public class AccountService implements UserDetailsService {
//
//    private final AccountRepository accountRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
//        this.accountRepository = accountRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = accountRepository.findByUsername(username);
//        if (account == null) {
//            throw new UsernameNotFoundException(username);
//        }
//
//        return User.builder()
//                .username(account.getUsername())
//                .password(account.getPassword())
//                .roles(account.getRole())
//                .build();
//    }
//    public Account createNew(Account account) {
//        account.encodePassword(passwordEncoder);
//        return accountRepository.save(account);
//    }
//
//}